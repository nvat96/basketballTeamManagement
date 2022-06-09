package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.repositories.CoachContractRepository;
import com.axonactive.basketball.services.CoachContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachContractServiceImpl implements CoachContractService {
    @Autowired
    CoachContractRepository coachContractRepository;

    @Override
    public List<CoachContract> findAll() {
        return coachContractRepository.findAll();
    }

    @Override
    public Optional<CoachContract> findByID(Integer id) {
        return coachContractRepository.findById(id);
    }

    @Override
    public CoachContract save(CoachContract coachContract) {
        return coachContractRepository.save(coachContract);
    }

    @Override
    public void deleteByID(Integer id) {
        coachContractRepository.deleteById(id);
    }
}
