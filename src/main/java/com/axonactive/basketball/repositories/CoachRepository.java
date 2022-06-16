package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Integer> {
    Optional<Coach> findByFirstNameAndLastNameLike(String firstName, String lastName);
    List<Coach> findByFirstNameLike(String firstName);
}
