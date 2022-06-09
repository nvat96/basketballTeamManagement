package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Arena;

import java.util.List;
import java.util.Optional;

public interface ArenaService {
    List<Arena> findAll();
    Optional<Arena> findByID(String name);
    Arena save(Arena arena);
    void deleteByID(String name);
}
