package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PlayerContractRepository extends JpaRepository<PlayerContract,Integer> {
    List<PlayerContract> findByPlayerId(Integer id);
    @Query("FROM PlayerContract " +
            "WHERE team.name LIKE CONCAT ('%',:teamName,'%')")
    List<PlayerContract> findByTeamNameLike(@Param("teamName") String teamName);
    @Query("SELECT new com.axonactive.basketball.services.dtos.PlayerWithContractDTO(pc.id,p.id," +
            "(p.firstName || ' ' || p.lastName),pc.dateCreated,pc.dateExpired,pc.salary,t.name) " +
            "FROM PlayerContract pc JOIN Player p " +
            "ON pc.player.id = p.id " +
            "JOIN Team t " +
            "ON pc.team.name = t.name " +
            "WHERE EXTRACT(YEAR FROM pc.dateExpired) = :year")
    List<PlayerWithContractDTO> findPlayerWithContractThatExpiredInThatYear(@Param("year")Integer year);
    @Query("SELECT new com.axonactive.basketball.services.dtos.PlayerWithContractDTO(pc.id,p.id," +
            "(p.firstName || ' ' || p.lastName),pc.dateCreated,pc.dateExpired,pc.salary,t.name) " +
            "FROM PlayerContract pc JOIN Player p " +
            "ON pc.player.id = p.id " +
            "JOIN Team t " +
            "ON pc.team.name = t.name " +
            "WHERE EXTRACT(YEAR FROM pc.dateCreated) = :year")
    List<PlayerWithContractDTO> findPlayerWithContractThatCreatedInThatYear(@Param("year")Integer year);
    @Query("SELECT new com.axonactive.basketball.services.dtos.PlayerWithContractDTO(pc.id,p.id," +
            "(p.firstName || ' ' || p.lastName),pc.dateCreated,pc.dateExpired,pc.salary,t.name) " +
            "FROM PlayerContract pc JOIN Player p " +
            "ON pc.player.id = p.id " +
            "JOIN Team t " +
            "ON pc.team.name = t.name " +
            "WHERE 2022 BETWEEN EXTRACT(YEAR FROM pc.dateCreated) AND EXTRACT(YEAR FROM pc.dateExpired) " +
            "AND pc.team.name LIKE CONCAT('%', :teamName,'%')")
    List<PlayerWithContractDTO> findPlayerContractThatAreActiveOfATeam(@Param("teamName") String teamName);
}
