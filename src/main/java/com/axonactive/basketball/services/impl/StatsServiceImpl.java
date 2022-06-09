package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.repositories.StatsRepository;
import com.axonactive.basketball.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    StatsRepository statsRepository;

    @Override
    public List<Stats> findAll() {
        return statsRepository.findAll();
    }

    @Override
    public Optional<Stats> findByID(Integer id) {
        return statsRepository.findById(id);
    }

    @Override
    public Stats save(Stats stat) {
        return statsRepository.save(stat);
    }

    @Override
    public void deleteByID(Integer id) {
        statsRepository.deleteById(id);
    }
}