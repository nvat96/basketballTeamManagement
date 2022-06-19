package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Optional<Owner> findByFirstNameAndLastName(String firstName, String lastName);
    List<Owner> findByFirstNameLike(String firstName);
    @Query("FROM Owner o " +
            "WHERE o.firstName LIKE CONCAT ('%', :firstName, '%') " +
            "AND o.lastName LIKE CONCAT ('%', :lastName, '%')")
    List<Owner> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
