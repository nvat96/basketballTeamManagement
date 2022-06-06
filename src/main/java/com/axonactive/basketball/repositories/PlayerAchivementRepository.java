package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.PlayerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerAchivementRepository extends JpaRepository<PlayerAchievement, Integer> {
}
