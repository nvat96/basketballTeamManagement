package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,String> {
    List<Team> findByNameLike(String name);
}
