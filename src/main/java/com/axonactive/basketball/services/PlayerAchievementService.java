package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.PlayerAchievement;

import java.util.List;
import java.util.Optional;

public interface PlayerAchievementService {
    List<PlayerAchievement> findAll();
    Optional<PlayerAchievement> findByID(Integer id);
    PlayerAchievement save(PlayerAchievement playerAchievement);
    void deleteByID(Integer id);
    List<PlayerAchievement> findByPlayerId(Integer playerID);
}
