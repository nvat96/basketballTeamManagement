package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.repositories.AgentRepository;
import com.axonactive.basketball.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgentServiceImpl implements AgentService {
    @Autowired
    AgentRepository agentRepository;

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Optional<Agent> findByID(Integer id) {
        return agentRepository.findById(id);
    }

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteByID(Integer id) {
        agentRepository.deleteById(id);
    }

    @Override
    public List<Agent> findByFirstNameLike(String firstName) {
        return agentRepository.findByFirstNameLike(firstName);
    }

    @Override
    public Optional<Agent> findByFirstNameAndLastNameLike(String firstName, String lastName) {
        return agentRepository.findByFirstNameAndLastNameLike(firstName, lastName);
    }
}
