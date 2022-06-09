package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.repositories.PlayerContractRepository;
import com.axonactive.basketball.services.PlayerContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerContractServiceImpl implements PlayerContractService {
    @Autowired
    PlayerContractRepository playerContractRepository;

    @Override
    public List<PlayerContract> findAll() {
        return playerContractRepository.findAll();
    }

    @Override
    public Optional<PlayerContract> findByID(Integer id) {
        return playerContractRepository.findById(id);
    }

    @Override
    public PlayerContract save(PlayerContract playerContract) {
        return playerContractRepository.save(playerContract);
    }

    @Override
    public void deleteByID(Integer id) {
        playerContractRepository.deleteById(id);
    }
}