package com.theagilemonkeys.hiring.crmtest.security;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TokenPayload {
    private final String username;
    private final ApplicationUser.Role role;

    public TokenPayload(String username, ApplicationUser.Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public ApplicationUser.Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("username", username)
                .append("role", role)
                .toString();
    }
}
