package com.theagilemonkeys.hiring.crmtest.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // We don't need this
                .cors().and().csrf().disable()
                .authorizeRequests()
                    .antMatchers("/graphql").permitAll()
                    .antMatchers("/graphiql", "/vendor/graphiql/*").permitAll()
                    .antMatchers("/h2-console/*").permitAll()
                    .anyRequest().denyAll()
                    .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // Disables sessions
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
