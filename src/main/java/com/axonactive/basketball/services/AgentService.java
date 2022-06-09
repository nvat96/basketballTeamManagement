package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Agent;

import java.util.List;
import java.util.Optional;

public interface AgentService {
    List<Agent> findAll();
    Optional<Agent> findByID(Integer id);
    Agent save(Agent agent);
    void deleteByID(Integer id);
}
