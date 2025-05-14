package com.kriNad.backend.repositories;

import com.kriNad.backend.model.personne.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


//Kristina
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}
