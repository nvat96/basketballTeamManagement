package com.axonactive.basketball.services;

import com.axonactive.basketball.apis.requests.ArenaRequest;
import com.axonactive.basketball.entities.Arena;

import java.util.List;

public interface ArenaService {
    List<Arena> findAll();
    Arena findByID(String name);
    Arena save(Arena arena);
    void deleteByID(String name);
    Arena create(Arena arena);
    Arena update(String name, ArenaRequest arenaRequest);
}
