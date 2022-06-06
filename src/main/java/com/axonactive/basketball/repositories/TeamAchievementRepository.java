package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.TeamAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamAchievementRepository extends JpaRepository<TeamAchievement,Integer> {
}
