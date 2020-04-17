package com.theagilemonkeys.hiring.crmtest.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public ApplicationUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ApplicationUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ApplicationUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public ApplicationUser setRole(Role role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("role", role)
                .toString();
    }

    public ApplicationUser merge(ApplicationUser updateRequest) {
        Optional.ofNullable(updateRequest.getUsername()).ifPresent(this::setUsername);
        Optional.ofNullable(updateRequest.getPassword()).ifPresent(this::setPassword);
        Optional.ofNullable(updateRequest.getRole()).ifPresent(this::setRole);

        return this;
    }

    public enum Role implements GrantedAuthority {
        NORMAL,
        ADMIN;

        @Override
        public String getAuthority() {
            return "ROLE_" + this.name();
        }
    }
}
