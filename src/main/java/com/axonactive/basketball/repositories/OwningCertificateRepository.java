package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.OwningCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwningCertificateRepository extends JpaRepository<OwningCertificate, Integer> {
}
