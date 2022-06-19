package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.repositories.PlayerContractRepository;
import com.axonactive.basketball.services.PlayerContractService;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerContractServiceImpl implements PlayerContractService {
    @Autowired
    PlayerContractRepository playerContractRepository;
    @Autowired
    OwnerServiceImpl ownerService;


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

    @Override
    public List<PlayerContract> findByPlayerId(Integer id) {
        return playerContractRepository.findByPlayerId(id);
    }

    @Override
    public List<PlayerContract> findByTeamNameLike(String teamName) {
        return playerContractRepository.findByTeamNameLike(teamName);
    }

    @Override
    public List<PlayerWithContractDTO> findPlayerWithContractThatExpiredInThatYear(Integer year) {
        if (year < 2010 || year > 2022)
            year = 2022;
        return playerContractRepository.findPlayerWithContractThatExpiredInThatYear(year);
    }

    @Override
    public List<PlayerWithContractDTO> findPlayerWithContractThatCreatedInThatYear(Integer year) {
        if (year < 2010 || year > 2022)
            year = 2022;
        return playerContractRepository.findPlayerWithContractThatCreatedInThatYear(year);
    }

    @Override
    public List<PlayerWithContractDTO> findPlayerContractThatAreActiveOfATeam(String teamName) {
        return playerContractRepository.findPlayerContractThatAreActiveOfATeam(teamName);
    }
}