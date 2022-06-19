package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerContractService {
    List<PlayerContract> findAll();
    Optional<PlayerContract> findByID(Integer id);
    PlayerContract save(PlayerContract playerContract);
    void deleteByID(Integer id);
    List<PlayerContract> findByPlayerId(Integer id);
    List<PlayerContract> findByTeamNameLike(String teamName);
    List<PlayerWithContractDTO> findPlayerWithContractThatExpiredInThatYear(Integer year);
    List<PlayerWithContractDTO> findPlayerWithContractThatCreatedInThatYear(Integer year);
    List<PlayerWithContractDTO> findPlayerContractThatAreActiveOfATeam(String teamName);
}
