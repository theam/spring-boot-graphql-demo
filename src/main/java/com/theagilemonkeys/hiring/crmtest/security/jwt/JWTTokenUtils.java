package com.theagilemonkeys.hiring.crmtest.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import com.theagilemonkeys.hiring.crmtest.security.TokenPayload;
import com.theagilemonkeys.hiring.crmtest.security.TokenUtils;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtils extends TokenUtils {
    public TokenPayload decodeToken(String authorizationHeader) {
        DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(authorizationHeader.replace(TOKEN_PREFIX, ""));

        return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(ApplicationUser.Role.class));
    }
}
