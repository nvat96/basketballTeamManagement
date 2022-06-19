package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.AgentContract;
import com.axonactive.basketball.repositories.AgentContractRepository;
import com.axonactive.basketball.services.AgentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentContractServiceImpl implements AgentContractService {
    @Autowired
    AgentContractRepository agentContractRepository;

    @Override
    public List<AgentContract> findAll() {
        return agentContractRepository.findAll();
    }

    @Override
    public Optional<AgentContract> findByID(Integer id) {
        return agentContractRepository.findById(id);
    }

    @Override
    public AgentContract save(AgentContract agentContract) {
        return agentContractRepository.save(agentContract);
    }

    @Override
    public void deleteByID(Integer id) {
        agentContractRepository.deleteById(id);
    }

    @Override
    public List<AgentContract> findByPlayerId(Integer playerID) {
        return agentContractRepository.findByPlayerId(playerID);
    }

    @Override
    public List<AgentContract> findByAgentId(Integer agentID) {
        return agentContractRepository.findByAgentId(agentID);
    }
}
