package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.apis.requests.CoachRequest;
import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.exceptions.ExceptionList;
import com.axonactive.basketball.repositories.CoachRepository;
import com.axonactive.basketball.services.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoachServiceImpl implements CoachService {
    @Autowired
    CoachRepository coachRepository;
    @Override
    public List<Coach> findAll() {
        return coachRepository.findAll();
    }

    @Override
    public Optional<Coach> findByID(Integer id) {
        if (!coachRepository.findById(id).isPresent())
            throw ExceptionList.coachNotFound();
        else return coachRepository.findById(id);
    }

    @Override
    public Coach save(Coach coach) {
        return coachRepository.save(coach);
    }

    @Override
    public void deleteByID(Integer id) {
        if (!coachRepository.findById(id).isPresent())
            throw ExceptionList.coachNotFound();
        else coachRepository.deleteById(id);
    }

    @Override
    public List<Coach> findByFirstNameLikeAndLastNameLike(String firstName, String lastName) {
        if (coachRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName).isEmpty())
            throw ExceptionList.badRequest("Not Found","No coach with first name and last name match");
        else return coachRepository.findByFirstNameLikeAndLastNameLike(firstName, lastName);
    }

    @Override
    public Coach create(CoachRequest coachRequest) {
        Coach coach = new Coach();
        coach.setFirstName(coachRequest.getFirstName());
        coach.setLastName(coachRequest.getLastName());
        coach.setDateOfBirth(coachRequest.getDateOfBirth());
        coach.setGender(Gender.valueOf(coachRequest.getGender()));
        coach.setNationality(coachRequest.getNationality());
        coach.setDateStarted(coachRequest.getDateStarted());
        coach.setSalaryExpected(coachRequest.getSalaryExpected());
        return coach;
    }

    @Override
    public Coach update(Integer id, CoachRequest coachRequest) {
        Coach coach = coachRepository.findById(id).orElseThrow(ExceptionList::coachNotFound);
        coach.setFirstName(coachRequest.getFirstName());
        coach.setLastName(coachRequest.getLastName());
        coach.setDateOfBirth(coachRequest.getDateOfBirth());
        coach.setGender(Gender.valueOf(coachRequest.getGender()));
        coach.setNationality(coachRequest.getNationality());
        coach.setDateStarted(coachRequest.getDateStarted());
        coach.setSalaryExpected(coachRequest.getSalaryExpected());
        return coach;
    }

    @Override
    public Optional<Coach> findByFirstNameAndLastName(String firstName, String lastName) {
        if(!coachRepository.findByFirstNameAndLastName(firstName, lastName).isPresent())
            throw ExceptionList.coachNotFound();
        else return coachRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Coach> findByFirstNameLike(String firstName) {
        return coachRepository.findByFirstNameLike(firstName);
    }
}
