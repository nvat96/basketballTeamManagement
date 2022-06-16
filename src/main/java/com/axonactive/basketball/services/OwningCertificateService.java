package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.OwningCertificate;

import java.util.List;
import java.util.Optional;

public interface OwningCertificateService {
    List<OwningCertificate> findAll();
    Optional<OwningCertificate> findByID(Integer id);
    OwningCertificate save(OwningCertificate owningCertificate);
    void deleteByID(Integer id);
}
