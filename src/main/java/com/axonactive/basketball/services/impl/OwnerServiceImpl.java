package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.repositories.OwnerRepository;
import com.axonactive.basketball.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;

    @Override
    public List<Owner> findAll() {
        return ownerRepository.findAll();
    }

    @Override
    public Optional<Owner> findByID(Integer id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void deleteByID(Integer id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Optional<Owner> findByName(String name) {
        return ownerRepository.findByName(name);
    }

    @Override
    public List<Owner> findByNameLike(String name) {
        return ownerRepository.findByNameLike(name);
    }
}
