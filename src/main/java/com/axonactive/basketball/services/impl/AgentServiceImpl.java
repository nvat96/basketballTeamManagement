package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.apis.requests.AgentRequest;
import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.exceptions.ExceptionList;
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
        Optional<Agent> agent = agentRepository.findById(id);
        if (!agent.isPresent())
            throw ExceptionList.agentNotFound();
        return agent;
    }

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent update(Integer agentID, AgentRequest agentRequest) {
        Agent agent = agentRepository.findById(agentID).orElseThrow(ExceptionList::agentNotFound);
        agent.setFirstName(agentRequest.getFirstName());
        agent.setLastName(agentRequest.getLastName());
        agent.setNationality(agentRequest.getNationality());
        agent.setGender(Gender.valueOf(agentRequest.getGender()));
        agent.setDateOfBirth(agentRequest.getDateOfBirth());
        agent.setPhoneNumber(agentRequest.getPhoneNumber());
        agent.setEmail(agentRequest.getEmail());
        return agentRepository.save(agent);
    }

    @Override
    public Agent create(AgentRequest agentRequest) {
        Agent agent = new Agent();
        agent.setFirstName(agentRequest.getFirstName());
        agent.setLastName(agentRequest.getLastName());
        agent.setNationality(agentRequest.getNationality());
        agent.setGender(Gender.valueOf(agentRequest.getGender()));
        agent.setDateOfBirth(agentRequest.getDateOfBirth());
        agent.setPhoneNumber(agentRequest.getPhoneNumber());
        agent.setEmail(agentRequest.getEmail());
        return agentRepository.save(agent);
    }

    @Override
    public void deleteByID(Integer id) {
        Optional<Agent> agent = agentRepository.findById(id);
        if (!agent.isPresent())
            throw ExceptionList.agentNotFound();
        agentRepository.deleteById(id);
    }

    @Override
    public List<Agent> findByFirstNameLike(String firstName) {
        return agentRepository.findByFirstNameLike(firstName);
    }
    @Override
    public Optional<Agent> findByFirstNameAndLastName(String firstName, String lastName) {
        return agentRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    @Override
    public List<Agent> findByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        return agentRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName);
    }
}
