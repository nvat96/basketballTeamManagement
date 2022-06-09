package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.CoachAchievement;

import java.util.List;
import java.util.Optional;

public interface CoachAchievementService {
    List<CoachAchievement> findAll();
    Optional<CoachAchievement> findByID(Integer id);
    CoachAchievement save(CoachAchievement coachAchievement);
    void deleteByID(Integer id);
}
