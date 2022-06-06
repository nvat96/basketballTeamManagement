package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepository extends JpaRepository<Stats,Integer> {
}
