package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.PlayerContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerContractRepository extends JpaRepository<PlayerContract,Integer> {
}
