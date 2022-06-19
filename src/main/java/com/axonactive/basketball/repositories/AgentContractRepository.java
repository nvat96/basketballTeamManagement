package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.AgentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentContractRepository extends JpaRepository<AgentContract,Integer> {
    List<AgentContract> findByPlayerId(Integer playerID);
    List<AgentContract> findByAgentId(Integer agentID);
}
