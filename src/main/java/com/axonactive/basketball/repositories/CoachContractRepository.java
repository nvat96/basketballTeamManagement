package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.services.dtos.CoachWithContractDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachContractRepository extends JpaRepository<CoachContract,Integer> {
    @Query("SELECT new com.axonactive.basketball.services.dtos.CoachWithContractDTO(cc.id,c.id," +
            "(c.firstName || ' ' || c.lastName),cc.dateCreated,cc.dateExpired,cc.salary,t.name) " +
            "FROM CoachContract cc JOIN Coach c " +
            "ON cc.coach.id = c.id " +
            "JOIN Team t " +
            "ON cc.team.name = t.name " +
            "WHERE 2022 BETWEEN EXTRACT(YEAR FROM cc.dateCreated) AND EXTRACT(YEAR FROM cc.dateExpired) " +
            "AND cc.team.name LIKE CONCAT('%', :teamName,'%')")
    List<CoachWithContractDTO> findCoachContractThatAreActiveOfATeam(@Param("teamName") String teamName);
}
