package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.PlayerWithStatsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<Stats,Integer> {
    List<Stats> findByPlayerId(Integer playerID);
    @Query("SELECT new com.axonactive.basketball.services.dtos.PlayerWithStatsDTO" +
            "((p.firstName || ' ' || p.lastName), s.gamePlayed, s.points, s.assists, s.steals, s.blocks, s.rebounds," +
            "s.threePointerPercentage, s.fieldGoalPercentage, s.freeThrowPercentage, s.season) " +
            "FROM Player p JOIN Stats s " +
            "ON p.id = s.player.id " +
            "WHERE p.id = :playerID " +
            "AND s.season = :season")
    PlayerWithStatsDTO findPlayerWithStatsInASeason(@Param("playerID")Integer playerID, @Param("season")Integer season);
}
