package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    List<Agent> findByFirstNameLike(String firstName);
    Optional<Agent> findByFirstNameAndLastName(String firstName, String lastName);
    @Query("FROM Agent a " +
            "WHERE a.firstName LIKE CONCAT ('%', :firstName, '%') " +
            "AND a.lastName LIKE CONCAT ('%', :lastName, '%')")
    List<Agent> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
