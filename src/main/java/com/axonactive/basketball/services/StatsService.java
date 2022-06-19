package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.PlayerWithStatsDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatsService {
    List<Stats> findAll();
    Optional<Stats> findByID(Integer id);
    Stats save(Stats stat);
    void deleteByID(Integer id);
    List<Stats> findByPlayerId(Integer playerID);
    PlayerWithStatsDTO findPlayerWithStatsInASeason(@Param("playerID")Integer playerID, @Param("season")Integer season);
}