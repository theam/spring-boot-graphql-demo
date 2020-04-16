package com.theagilemonkeys.hiring.crmtest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.ErrorManager;

import static com.theagilemonkeys.hiring.crmtest.security.JWTAuthenticationFilter.HEADER_STRING;
import static com.theagilemonkeys.hiring.crmtest.security.JWTAuthenticationFilter.SECRET;
import static com.theagilemonkeys.hiring.crmtest.security.JWTAuthenticationFilter.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .build()
                    .verify(token.replace(TOKEN_PREFIX, ""));

            String userId = decodedToken.getSubject();
            ApplicationUser.Role userRole = decodedToken.getClaim("role").as(ApplicationUser.Role.class);

            if (userId != null) {
                return new UsernamePasswordAuthenticationToken(userId, null, Collections.singletonList(userRole));
            }

            LOGGER.error("Valid token contains no user info");
        }
        return null;
    }
}