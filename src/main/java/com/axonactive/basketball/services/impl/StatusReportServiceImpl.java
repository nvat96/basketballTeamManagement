package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.repositories.StatusReportRepository;
import com.axonactive.basketball.services.StatusReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusReportServiceImpl implements StatusReportService {
    @Autowired
    StatusReportRepository statusReportRepository;

    @Override
    public List<StatusReport> findAll() {
        return statusReportRepository.findAll();
    }

    @Override
    public Optional<StatusReport> findByID(Integer id) {
        return statusReportRepository.findById(id);
    }

    @Override
    public StatusReport save(StatusReport statusReport) {
        return statusReportRepository.save(statusReport);
    }

    @Override
    public void deleteByID(Integer id) {
        statusReportRepository.deleteById(id);
    }

    @Override
    public List<StatusReport> findByPlayerId(Integer playerID) {
        return statusReportRepository.findByPlayerId(playerID);
    }
}