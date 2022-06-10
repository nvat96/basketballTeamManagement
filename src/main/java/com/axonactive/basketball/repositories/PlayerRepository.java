package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Optional<Player> findByName(String name);
    List<Player> findByNameLike(String name);
}
