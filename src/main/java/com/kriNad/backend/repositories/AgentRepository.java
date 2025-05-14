package com.kriNad.backend.repositories;

import com.kriNad.backend.model.personne.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


//Kristina
public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByEmail(String email);
}