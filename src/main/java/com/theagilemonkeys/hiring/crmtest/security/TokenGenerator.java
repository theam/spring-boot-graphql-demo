package com.theagilemonkeys.hiring.crmtest.security;

public interface TokenGenerator {
    String build(Object id, Object role);
}
