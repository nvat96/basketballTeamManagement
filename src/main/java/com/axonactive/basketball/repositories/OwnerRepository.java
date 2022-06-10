package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Optional<Owner> findByName(String name);
    List<Owner> findByNameLike(String name);
}
