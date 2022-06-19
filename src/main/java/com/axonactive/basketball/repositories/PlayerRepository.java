package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import com.axonactive.basketball.services.dtos.PlayerWithTeamDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Optional<Player> findByFirstNameAndLastName(String firstName, String lastName);
    List<Player> findByFirstNameLike(String firstName);
    @Query("SELECT new com.axonactive.basketball.services.dtos.PlayerWithTeamDTO(p.id, (p.firstName || ' ' || p.lastName), t.name) " +
            "FROM Player p JOIN PlayerContract pc " +
            "ON p.id = pc.player.id " +
            "JOIN Team t ON pc.team.name = t.name " +
            "WHERE :inputYear BETWEEN EXTRACT(YEAR FROM pc.dateCreated) AND EXTRACT(YEAR FROM pc.dateExpired) " +
            "AND t.name LIKE CONCAT('%',:teamName,'%') " +
            "GROUP BY p.id, t.name")
    List<PlayerWithTeamDTO> findPlayerThatPlayForThatTeamAtThatYear(@Param("inputYear")Integer inputYear, @Param("teamName")String teamName );
    @Query("FROM Player p " +
            "WHERE p.firstName LIKE CONCAT ('%', :firstName, '%') " +
            "AND p.lastName LIKE CONCAT ('%', :lastName, '%')")
    List<Player> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName,@Param("lastName") String lastName);
}
