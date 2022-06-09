package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.PlayerContract;

import java.util.List;
import java.util.Optional;

public interface PlayerContractService {
    List<PlayerContract> findAll();
    Optional<PlayerContract> findByID(Integer id);
    PlayerContract save(PlayerContract playerContract);
    void deleteByID(Integer id);
}
