package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Staff;

import java.util.List;
import java.util.Optional;

public interface StaffService {
    List<Staff> findAll();
    Optional<Staff> findByID(Integer id);
    Staff save(Staff staff);
    void deleteByID(Integer id);
}