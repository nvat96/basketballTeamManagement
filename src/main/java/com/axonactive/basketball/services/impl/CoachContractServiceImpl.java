package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.repositories.CoachContractRepository;
import com.axonactive.basketball.services.CoachContractService;
import com.axonactive.basketball.services.dtos.CoachWithContractDTO;
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

    @Override
    public List<CoachWithContractDTO> findCoachContractThatAreActiveOfATeam(String teamName) {
        return coachContractRepository.findCoachContractThatAreActiveOfATeam(teamName);
    }

    @Override
    public List<CoachContract> findByCoachId(Integer coachID) {
        return coachContractRepository.findByCoachId(coachID);
    }

    @Override
    public List<CoachContract> findByTeamNameLike(String teamName) {
        return coachContractRepository.findByTeamNameLike(teamName);
    }
}
