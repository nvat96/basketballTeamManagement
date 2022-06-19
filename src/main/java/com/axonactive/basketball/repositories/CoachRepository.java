package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Integer> {
    Optional<Coach> findByFirstNameAndLastName(String firstName, String lastName);
    List<Coach> findByFirstNameLike(String firstName);
    @Query("FROM Coach c " +
            "WHERE c.firstName LIKE CONCAT ('%', :firstName, '%') " +
            "AND c.lastName LIKE CONCAT ('%', :lastName, '%')")
    List<Coach> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
