package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Integer> {
    Optional<Staff> findByName(String name);
    List<Staff> findByNameLike(String name);
}
