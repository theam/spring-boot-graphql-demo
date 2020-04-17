package com.theagilemonkeys.hiring.crmtest.security;

import com.theagilemonkeys.hiring.crmtest.security.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    protected TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Required to make H2-console work
                .headers().frameOptions().disable()
                    .and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/graphql").permitAll()
                    .antMatchers("/graphiql", "/vendor/graphiql/*").permitAll()
                    .antMatchers("/h2-console/*").permitAll()
                    .anyRequest().denyAll()
                    .and()
                .addFilter(new JWTAuthorizationFilter(getAuthenticationManager(), tokenUtils))
                // Disables sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
