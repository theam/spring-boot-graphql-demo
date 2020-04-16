package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import com.theagilemonkeys.hiring.crmtest.repositories.ApplicationUserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@Secured("ROLE_ADMIN")
public class UserMutationResolver implements GraphQLMutationResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApplicationUserRepository userRepository;

    public ApplicationUser createUser(ApplicationUser user) {
        // TODO: Encrypt password

        if (user.getRole() == null) {
            user.setRole(ApplicationUser.Role.NORMAL);
        }

        LOGGER.info("Received request to create an user with: {}", user);



        return userRepository.save(user);
    }

    public ApplicationUser updateUser(ApplicationUser updateRequest) {
        if (updateRequest.getId() == null) {
            throw new IllegalArgumentException("The update request must include an ID");
        }
        if (updateRequest.getUsername() == null && updateRequest.getPassword() == null && updateRequest.getRole() == null) {
            throw new IllegalArgumentException("The update request must include values for either" +
                    "the username, password or role");
        }

        LOGGER.info("Update request received: {}", updateRequest);

        ApplicationUser currentUser = userRepository.findById(updateRequest.getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        currentUser.merge(updateRequest);

        return userRepository.save(currentUser);
    }

    public boolean deleteUser(Long id) {
        LOGGER.info("Received request to delete user with id: {}", id);
        userRepository.deleteById(id);

        return true;
    }
}
