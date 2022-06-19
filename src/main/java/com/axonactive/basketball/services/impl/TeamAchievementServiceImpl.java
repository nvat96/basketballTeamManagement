package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.TeamAchievement;
import com.axonactive.basketball.repositories.TeamAchievementRepository;
import com.axonactive.basketball.services.TeamAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamAchievementServiceImpl implements TeamAchievementService {
    @Autowired
    TeamAchievementRepository teamAchievementRepository;

    @Override
    public List<TeamAchievement> findAll() {
        return teamAchievementRepository.findAll();
    }

    @Override
    public Optional<TeamAchievement> findByID(Integer id) {
        return teamAchievementRepository.findById(id);
    }

    @Override
    public TeamAchievement save(TeamAchievement teamAchievement) {
        return teamAchievementRepository.save(teamAchievement);
    }

    @Override
    public void deleteByID(Integer id) {
        teamAchievementRepository.deleteById(id);
    }

    @Override
    public List<TeamAchievement> findByTeamNameLike(String teamName) {
        return teamAchievementRepository.findByTeamNameLike(teamName);
    }
}