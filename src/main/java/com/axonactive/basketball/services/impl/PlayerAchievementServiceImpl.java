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
    PlayerAchievementRepository agentRepository;

    @Override
    public List<PlayerAchievement> findAll() {
        return agentRepository.findAll();
    }

    @Override
    public Optional<PlayerAchievement> findByID(Integer id) {
        return agentRepository.findById(id);
    }

    @Override
    public PlayerAchievement save(PlayerAchievement agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteByID(Integer id) {
        agentRepository.deleteById(id);
    }
}
