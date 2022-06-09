package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.StatusReport;

import java.util.List;
import java.util.Optional;

public interface StatusReportService {
    List<StatusReport> findAll();
    Optional<StatusReport> findByID(Integer id);
    StatusReport save(StatusReport statusReport);
    void deleteByID(Integer id);
}