package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<Coach> findAll();
    Optional<Coach> findByID(Integer id);
    Coach save(Coach coach);
    void deleteByID(Integer id);
    Optional<Coach> findByName(String name);
    List<Coach> findByNameLike(String name);
}
