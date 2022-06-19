package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.repositories.OwnerRepository;
import com.axonactive.basketball.services.OwnerService;
import com.axonactive.basketball.services.dtos.CoachWithContractDTO;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    OwnerRepository ownerRepository;
    @Autowired
    PlayerContractServiceImpl playerContractService;
    @Autowired
    CoachContractServiceImpl coachContractService;

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
    public Optional<Owner> findByFirstNameAndLastName(String firstName,String lastName) {
        return ownerRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    @Override
    public List<Owner> findByFirstNameLike(String firstName) {
        return ownerRepository.findByFirstNameLike(firstName);
    }
    @Override
    public List<Owner> findByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        return ownerRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName);
    }
    @Override
    public Double calculateSalaryMustPay(String teamName) {
        Double totalPayment = 0.0;
        List<PlayerWithContractDTO> players = playerContractService.findPlayerContractThatAreActiveOfATeam(teamName);
        List<CoachWithContractDTO> coaches = coachContractService.findCoachContractThatAreActiveOfATeam(teamName);
        for (PlayerWithContractDTO p: players) {
            totalPayment += p.getSalary();
        }
        for (CoachWithContractDTO c: coaches) {
            totalPayment += c.getSalary();
        }
        return totalPayment;
    }
}
