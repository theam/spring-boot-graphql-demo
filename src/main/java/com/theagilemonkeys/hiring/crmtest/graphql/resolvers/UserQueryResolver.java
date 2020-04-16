package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import com.theagilemonkeys.hiring.crmtest.repositories.ApplicationUserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Secured("ROLE_ADMIN")
public class UserQueryResolver implements GraphQLQueryResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public List<ApplicationUser> getUsers() {
        return applicationUserRepository.findAll();
    }
}
