package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> findAll();
    Optional<Player> findByID(Integer id);
    Player save(Player player);
    void deleteByID(Integer id);
    Optional<Player> findByFirstNameAndLastNameLike(String firstName, String lastName);
    List<Player> findByFirstNameLike(String firstName);
}
