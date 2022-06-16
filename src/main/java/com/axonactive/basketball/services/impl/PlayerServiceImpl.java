package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.repositories.PlayerRepository;
import com.axonactive.basketball.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findByID(Integer id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteByID(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Optional<Player> findByFirstNameAndLastNameLike(String firstName, String lastName) {
        return playerRepository.findByFirstNameAndLastNameLike(firstName, lastName);
    }

    @Override
    public List<Player> findByFirstNameLike(String firstName) {
        return playerRepository.findByFirstNameLike(firstName);
    }
}
