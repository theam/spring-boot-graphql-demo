package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import com.theagilemonkeys.hiring.crmtest.repositories.ApplicationUserRepository;
import com.theagilemonkeys.hiring.crmtest.security.TokenGenerator;
import graphql.GraphQLException;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenResolver implements GraphQLMutationResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    public String getToken(String username, String password) {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        // TODO: Hash password before checking (bcrypt comparison)
        if (applicationUser == null || !applicationUser.getPassword().equals(password)) {
            throw new GraphQLException("Invalid credentials");
        }

        return tokenGenerator.build(applicationUser.getId(), applicationUser.getRole());
    }


}
