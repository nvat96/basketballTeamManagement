package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.repositories.StaffRepository;
import com.axonactive.basketball.services.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> findByID(Integer id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public void deleteByID(Integer id) {
        staffRepository.deleteById(id);
    }

    @Override
    public Optional<Staff> findByName(String name) {
        return staffRepository.findByName(name);
    }

    @Override
    public List<Staff> findByNameLike(String name) {
        return staffRepository.findByNameLike(name);
    }
}