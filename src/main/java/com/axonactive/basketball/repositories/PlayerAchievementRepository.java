package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.PlayerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, Integer> {
    List<PlayerAchievement> findByPlayerId(Integer playerID);
}
