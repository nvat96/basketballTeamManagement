package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface StatusReportService {
    List<StatusReport> findAll();
    Optional<StatusReport> findByID(Integer id);
    StatusReport save(StatusReport statusReport);
    void deleteByID(Integer id);
    List<StatusReport> findByPlayerId(Integer playerID);
    List<StatusReport> findByTeamNameAndYear(String teamName, Integer year);
}