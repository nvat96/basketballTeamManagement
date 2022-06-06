package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.AgentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentContractRepository extends JpaRepository<AgentContract,Integer> {
}
