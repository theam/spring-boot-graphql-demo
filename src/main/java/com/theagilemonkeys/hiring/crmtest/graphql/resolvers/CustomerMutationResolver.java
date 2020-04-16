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

@Component
@Secured("IS_AUTHENTICATED_FULLY")
public class CustomerMutationResolver implements GraphQLMutationResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository customerRepository;

    // TODO: Improve erroring! E.g. delete that no exists

    public Customer createCustomer(Customer newCustomer) {
        LOGGER.info("Received request to create customer: {}", newCustomer);
        return customerRepository.save(newCustomer);
    }

    public Customer updateCustomer(Customer updateRequest) {
        if (updateRequest.getName() == null &&
                updateRequest.getSurname() == null &&
                updateRequest.getPictureUrl() == null) {
            throw new IllegalArgumentException("The update request should include values for either name, surname or picture");
        }

        LOGGER.info("Update request received: {}", updateRequest);

        Customer currentCustomer = customerRepository.findById(updateRequest.getId()).orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        currentCustomer.merge(updateRequest);

        return customerRepository.save(currentCustomer);
    }

    public boolean deleteCustomer(Long id) {
        LOGGER.info("Received request to delete customer with id: {}", id);
        customerRepository.deleteById(id);

        return true;
    }
}
