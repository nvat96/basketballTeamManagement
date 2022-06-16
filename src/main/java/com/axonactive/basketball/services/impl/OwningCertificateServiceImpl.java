package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.OwningCertificate;
import com.axonactive.basketball.repositories.OwningCertificateRepository;
import com.axonactive.basketball.services.OwningCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwningCertificateServiceImpl implements OwningCertificateService {
    @Autowired
    OwningCertificateRepository owningCertificateRepository;

    @Override
    public List<OwningCertificate> findAll() {
        return owningCertificateRepository.findAll();
    }

    @Override
    public Optional<OwningCertificate> findByID(Integer id) {
        return owningCertificateRepository.findById(id);
    }

    @Override
    public OwningCertificate save(OwningCertificate owningCertificate) {
        return owningCertificateRepository.save(owningCertificate);
    }

    @Override
    public void deleteByID(Integer id) {
        owningCertificateRepository.deleteById(id);
    }
}