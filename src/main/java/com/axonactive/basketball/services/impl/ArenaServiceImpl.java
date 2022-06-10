package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.repositories.ArenaRepository;
import com.axonactive.basketball.services.ArenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArenaServiceImpl implements ArenaService {
    @Autowired
    ArenaRepository arenaRepository;
    @Override
    public List<Arena> findAll() {
        return arenaRepository.findAll();
    }

    @Override
    public Optional<Arena> findByID(String name) {
        return arenaRepository.findById(name);
    }

    @Override
    public Arena save(Arena arena) {
        return arenaRepository.save(arena);
    }

    @Override
    public void deleteByID(String name) {
        arenaRepository.deleteById(name);
    }

}
