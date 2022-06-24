package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.apis.requests.ArenaRequest;
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
    public Arena findByID(String name) {
        Optional<Arena> arena =  arenaRepository.findById(name);
        if (!arena.isPresent())
            throw ExceptionList.arenaNotFound();
        return arena.get();
    }

    @Override
    public Arena save(Arena arena) {
        return arenaRepository.save(arena);
    }

    @Override
    public void deleteByID(String name) {
        arenaRepository.deleteById(name);
    }

    @Override
    public Arena create(Arena arena) {
        for (Arena a: arenaRepository.findAll()) {
            if (arena.getName().equals(a.getName())) {
                throw ExceptionList.badRequest("Arena name already exist", "Can't create new arena");

            }
        }
        return arenaRepository.save(arena);
    }

    @Override
    public Arena update(String name, ArenaRequest arenaRequest) {
        Arena arena = arenaRepository.findById(name).orElseThrow(ExceptionList::arenaNotFound);
        arena.setCapacity(arenaRequest.getCapacity());
        arena.setLocation(arenaRequest.getLocation());
        arena.setDateBuilt(arenaRequest.getDateBuilt());
        return arenaRepository.save(arena);
    }

}
