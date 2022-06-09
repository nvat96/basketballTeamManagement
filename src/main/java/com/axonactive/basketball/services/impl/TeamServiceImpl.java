package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.repositories.TeamRepository;
import com.axonactive.basketball.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findByID(String name) {
        return teamRepository.findById(name);
    }

    @Override
    public Team save(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteByID(String name) {
        teamRepository.deleteById(name);
    }
}
