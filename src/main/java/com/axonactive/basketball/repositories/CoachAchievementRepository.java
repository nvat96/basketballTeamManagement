package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.CoachAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachAchievementRepository extends JpaRepository<CoachAchievement,Integer> {
}
