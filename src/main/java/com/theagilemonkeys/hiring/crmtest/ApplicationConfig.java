package com.theagilemonkeys.hiring.crmtest;

import com.theagilemonkeys.hiring.crmtest.exceptions.DuplicateEntryException;
import graphql.GraphQLError;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * We should be extending GraphQLErrorHandler instead, but it's broken in v7.0.1
 * Fix to be released in v7.1.0 https://github.com/graphql-java-kickstart/graphql-spring-boot/issues/379
 */

@Component
public class ApplicationConfig {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(AccessDeniedException.class)
    public GraphQLError exceptionHandler(AccessDeniedException e) {
        return new ThrowableGraphQLError(e, "Unauthorized request");
    }

    @ExceptionHandler(DuplicateEntryException.class)
    public GraphQLError exceptionHandler(DuplicateEntryException e) {
        return new ThrowableGraphQLError(e, "Duplicated entry");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public GraphQLError exceptionHandler(EntityNotFoundException e) {
        return new ThrowableGraphQLError(e, "Entity not found");
    }
}
