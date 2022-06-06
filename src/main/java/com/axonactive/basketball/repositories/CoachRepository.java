package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach,Integer> {
}
