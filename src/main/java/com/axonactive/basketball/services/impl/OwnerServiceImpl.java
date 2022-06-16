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
    public Optional<Owner> findByFirstNameAndLastNameLike(String firstName,String lastName) {
        return ownerRepository.findByFirstNameAndLastNameLike(firstName, lastName);
    }

    @Override
    public List<Owner> findByFirstNameLike(String firstName) {
        return ownerRepository.findByFirstNameLike(firstName);
    }
}
