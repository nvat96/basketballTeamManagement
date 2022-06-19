package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {
    Optional<Staff> findByFirstNameAndLastName(String firstName, String lastName);
    List<Staff> findByFirstNameLike(String firstName);
    @Query("FROM Staff s " +
            "WHERE s.firstName LIKE CONCAT ('%', :firstName, '%') " +
            "AND s.lastName LIKE CONCAT ('%', :lastName, '%')")
    List<Staff> findByFirstNameLikeAndLastNameLike(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
