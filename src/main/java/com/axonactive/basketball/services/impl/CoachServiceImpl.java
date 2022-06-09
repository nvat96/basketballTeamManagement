package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.repositories.CoachRepository;
import com.axonactive.basketball.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    CoachRepository coachRepository;
    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Optional<Coach> findByID(Integer id) {
        return coachRepository.findById(id);
    }

    @Override
    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void deleteByID(Integer id) {
        coachRepository.deleteById(id);
    }
}
