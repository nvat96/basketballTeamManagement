package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Stats;

import java.util.List;
import java.util.Optional;

public interface StatsService {
    List<Stats> findAll();
    Optional<Stats> findByID(Integer id);
    Stats save(Stats stat);
    void deleteByID(Integer id);
}