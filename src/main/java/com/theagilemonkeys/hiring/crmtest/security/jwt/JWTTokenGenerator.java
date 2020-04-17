package com.theagilemonkeys.hiring.crmtest.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.theagilemonkeys.hiring.crmtest.security.TokenGenerator;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.theagilemonkeys.hiring.crmtest.security.TokenUtils.EXPIRATION_TIME;
import static com.theagilemonkeys.hiring.crmtest.security.TokenUtils.SECRET;

@Component
public class JWTTokenGenerator implements TokenGenerator {
    @Override
    public String build(Object id, Object role) {
        return JWT.create()
                .withSubject(id.toString())
                .withClaim("role", role.toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
    }
}
