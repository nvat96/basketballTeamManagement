package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Arena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArenaRepository extends JpaRepository<Arena,String> {
}
