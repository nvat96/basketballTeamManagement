package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team,String> {
}
