package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import com.theagilemonkeys.hiring.crmtest.entities.Customer;
import com.theagilemonkeys.hiring.crmtest.repositories.CustomerRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

// TODO: Add support for pictures

@Component
@Secured("IS_AUTHENTICATED_FULLY")
public class CustomerMutationResolver implements GraphQLMutationResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer newCustomer) {
        LOGGER.info("Received request to create customer: {}", newCustomer);

        // TODO: Add createdBy+updatedBy

        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Customer updateRequest) {
        if (updateRequest.getId() == null) {
            throw new IllegalArgumentException("The update request must include an ID");
        }
        if (updateRequest.getName() == null &&
                updateRequest.getSurname() == null &&
                updateRequest.getPictureUrl() == null) {
            throw new IllegalArgumentException("The update request must include values for either name, surname or picture");
        }

        LOGGER.info("Update request received: {}", updateRequest);

        Customer currentCustomer = customerRepository.findById(updateRequest.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        currentCustomer.merge(updateRequest);

        // TODO: Add updatedBy

        return customerRepository.save(currentCustomer);
    }

    public boolean deleteCustomer(Long id) {
        LOGGER.info("Received request to delete customer with id: {}", id);
        customerRepository.deleteById(id);

        return true;
    }
}
