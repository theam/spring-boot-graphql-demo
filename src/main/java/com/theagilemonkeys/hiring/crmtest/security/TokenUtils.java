package com.theagilemonkeys.hiring.crmtest.security;

public abstract class TokenUtils {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET = "MySup3r Secret!";
    public static final long EXPIRATION_TIME = 1 * 60 * 60 * 1000; // 1 hour in ms

    abstract public TokenPayload decodeToken(String authorizationHeader);
}
