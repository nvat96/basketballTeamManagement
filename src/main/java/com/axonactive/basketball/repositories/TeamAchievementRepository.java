package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.TeamAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamAchievementRepository extends JpaRepository<TeamAchievement,Integer> {
    @Query("FROM TeamAchievement " +
            "WHERE team.name LIKE CONCAT ('%', :teamName, '%')")
    List<TeamAchievement> findByTeamNameLike(@Param("teamName") String teamName);
}
