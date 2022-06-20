package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface StatusReportRepository extends JpaRepository<StatusReport,Integer> {
    List<StatusReport> findByPlayerId(Integer playerID);
    @Query("SELECT DISTINCT sr " +
            "FROM StatusReport sr JOIN Player p " +
            "ON sr.player.id = p.id " +
            "JOIN PlayerContract pc " +
            "ON pc.player.id = p.id " +
            "JOIN Team t " +
            "ON pc.team.name = t.name " +
            "WHERE :year = EXTRACT(YEAR FROM sr.dateInjured) " +
            "AND t.name LIKE CONCAT('%', :teamName, '%') " )
    List<StatusReport> findByTeamNameAndYear(@Param("teamName") String teamName, @Param("year") Integer year);
}
