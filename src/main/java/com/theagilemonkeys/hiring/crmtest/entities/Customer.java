package com.theagilemonkeys.hiring.crmtest.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    private String createdBy;

    private String updatedBy;

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Customer setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Customer setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Customer setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("surname", surname)
                .append("createdBy", createdBy)
                .append("updatedBy", updatedBy)
                .toString();
    }

    public Customer merge(Customer updateRequest) {
        Optional.ofNullable(updateRequest.getName()).ifPresent(this::setName);
        Optional.ofNullable(updateRequest.getSurname()).ifPresent(this::setSurname);

        return this;
    }
}
