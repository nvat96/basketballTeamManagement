package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.StatusReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport,Integer> {
    List<StatusReport> findByPlayerId(Integer playerID);
}
