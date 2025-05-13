package com.kriNad.backend.repositories;

import com.kriNad.backend.model.personne.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Kristina
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}