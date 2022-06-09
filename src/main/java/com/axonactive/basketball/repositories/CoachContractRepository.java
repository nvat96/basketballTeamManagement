package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.entities.CoachContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachContractRepository extends JpaRepository<CoachContract,Integer> {
}
