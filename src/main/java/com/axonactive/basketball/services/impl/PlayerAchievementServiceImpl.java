package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.PlayerAchievement;
import com.axonactive.basketball.repositories.PlayerAchievementRepository;
import com.axonactive.basketball.services.PlayerAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerAchievementServiceImpl implements PlayerAchievementService {
    @Autowired
    PlayerAchievementRepository playerAchievement;

    @Override
    public List<PlayerAchievement> findAll() {
        return playerAchievement.findAll();
    }

    @Override
    public Optional<PlayerAchievement> findByID(Integer id) {
        return playerAchievement.findById(id);
    }

    @Override
    public PlayerAchievement save(PlayerAchievement agent) {
        return playerAchievement.save(agent);
    }

    @Override
    public void deleteByID(Integer id) {
        playerAchievement.deleteById(id);
    }

    @Override
    public List<PlayerAchievement> findByPlayerId(Integer playerID) {
        return playerAchievement.findByPlayerId(playerID);
    }
}
