package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.exceptions.ExceptionList;
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
    public Optional<Arena> findByID(String arenaName) {
        Optional<Arena> arena = arenaRepository.findById(arenaName);
        if (!arena.isPresent())
            throw ExceptionList.arenaNotFound();
        return arena;
    }

    @Override
    public Arena save(Arena arena) {
        return arenaRepository.save(arena);
    }

    @Override
    public void deleteByID(String arenaName) {
        Optional<Arena> arena = arenaRepository.findById(arenaName);
        if (!arena.isPresent())
            throw ExceptionList.arenaNotFound();
        arenaRepository.deleteById(arenaName);
    }

    @Override
    public Arena create(Arena arena) {
        Arena createArena = new Arena();
        if (!arenaRepository.findById(arena.getName()).isPresent()) {
            createArena.setName(arena.getName());
            createArena.setDateBuilt(arena.getDateBuilt());
            createArena.setLocation(arena.getLocation());
            createArena.setCapacity(arena.getCapacity());
            return arenaRepository.save(createArena);
        }
        else return null;
    }

    @Override
    public Arena update(String arenaName, Arena arena) {
        Arena updateArena = arenaRepository.findById(arenaName).orElseThrow(ExceptionList::arenaNotFound);
        updateArena.setDateBuilt(arena.getDateBuilt());
        updateArena.setLocation(arena.getLocation());
        updateArena.setCapacity(arena.getCapacity());
        return arenaRepository.save(updateArena);
    }
}
