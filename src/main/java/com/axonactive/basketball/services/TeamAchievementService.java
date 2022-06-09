package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.TeamAchievement;

import java.util.List;
import java.util.Optional;

public interface TeamAchievementService {
    List<TeamAchievement> findAll();
    Optional<TeamAchievement> findByID(Integer id);
    TeamAchievement save(TeamAchievement teamAchievement);
    void deleteByID(Integer id);
}