package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findByNameLike(String name);
    Optional<Agent> findByName(String name);
}
