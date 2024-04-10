package com.example.demo.repository;

import com.example.demo.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {

    Optional<Agent> findByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<Agent> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

}


