package com.theagilemonkeys.hiring.crmtest.graphql.resolvers;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import com.theagilemonkeys.hiring.crmtest.entities.Customer;
import com.theagilemonkeys.hiring.crmtest.repositories.ApplicationUserRepository;
import com.theagilemonkeys.hiring.crmtest.repositories.CustomerRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Secured("IS_AUTHENTICATED_FULLY")
public class CustomerQueryResolver implements GraphQLQueryResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }
}
