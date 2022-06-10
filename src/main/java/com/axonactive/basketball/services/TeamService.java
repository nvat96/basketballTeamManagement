package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    List<Team> findAll();
    Optional<Team> findByID(String name);
    Team save(Team team);
    void deleteByID(String name);
    List<Team> findByNameLike(String name);
}
