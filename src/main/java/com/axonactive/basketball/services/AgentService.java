package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Agent;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AgentService {
    List<Agent> findAll();
    Optional<Agent> findByID(Integer id);
    Agent save(Agent agent);
    void deleteByID(Integer id);
    List<Agent> findByFirstNameLike(String firstName);
    Optional<Agent> findByFirstNameAndLastName(String firstName, String lastName);
    List<Agent> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}
