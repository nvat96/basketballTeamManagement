package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.services.dtos.CoachWithContractDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CoachContractService {
    List<CoachContract> findAll();
    Optional<CoachContract> findByID(Integer id);
    CoachContract save(CoachContract coachContract);
    void deleteByID(Integer id);
    List<CoachWithContractDTO> findCoachContractThatAreActiveOfATeam(String teamName);
    List<CoachContract> findByCoachId(Integer coachID);
    List<CoachContract> findByTeamNameLike(String teamName);
}
