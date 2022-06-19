package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.repositories.CoachAchievementRepository;
import com.axonactive.basketball.services.CoachAchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachAchievementServiceImpl implements CoachAchievementService {
    @Autowired
    CoachAchievementRepository coachAchievementRepository;

    @Override
    public List<CoachAchievement> findAll() {
        return coachAchievementRepository.findAll();
    }

    @Override
    public Optional<CoachAchievement> findByID(Integer id) {
        return coachAchievementRepository.findById(id);
    }

    @Override
    public CoachAchievement save(CoachAchievement coachAchievement) {
        return coachAchievementRepository.save(coachAchievement);
    }

    @Override
    public void deleteByID(Integer id) {
        coachAchievementRepository.deleteById(id);
    }

    @Override
    public List<CoachAchievement> findByCoachId(Integer id) {
        return coachAchievementRepository.findByCoachId(id);
    }
}
