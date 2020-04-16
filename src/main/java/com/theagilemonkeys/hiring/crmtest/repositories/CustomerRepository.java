package com.theagilemonkeys.hiring.crmtest.repositories;

import com.theagilemonkeys.hiring.crmtest.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
