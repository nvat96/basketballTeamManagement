package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Staff;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> findAll();
    Optional<Staff> findByID(Integer id);
    Staff save(Staff staff);
    void deleteByID(Integer id);
    Optional<Staff> findByFirstNameAndLastName(String firstName, String lastName);
    List<Staff> findByFirstNameLike(String name);
    List<Staff> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}