package com.theagilemonkeys.hiring.crmtest.repositories;

import com.theagilemonkeys.hiring.crmtest.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
