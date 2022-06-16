package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    List<Owner> findAll();
    Optional<Owner> findByID(Integer id);
    Owner save(Owner owner);
    void deleteByID(Integer id);
    Optional<Owner> findByFirstNameAndLastNameLike(String firstName, String lastName);
    List<Owner> findByFirstNameLike(String firstName);
}
