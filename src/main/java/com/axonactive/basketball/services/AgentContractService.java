package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.AgentContract;

import java.util.List;
import java.util.Optional;

public interface AgentContractService {
    List<AgentContract> findAll();
    Optional<AgentContract> findByID(Integer id);
    AgentContract save(AgentContract agentContract);
    void deleteByID(Integer id);
    List<AgentContract> findByPlayerId(Integer playerID);
    List<AgentContract> findByAgentId(Integer agentID);
}
