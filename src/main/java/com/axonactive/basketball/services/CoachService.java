package com.axonactive.basketball.services;

import com.axonactive.basketball.apis.requests.CoachRequest;
import com.axonactive.basketball.entities.Coach;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CoachService {
    List<Coach> findAll();
    Optional<Coach> findByID(Integer id);
    Coach save(Coach coach);
    void deleteByID(Integer id);
    Optional<Coach> findByFirstNameAndLastName(String firstName, String lastName);
    List<Coach> findByFirstNameLike(String firstName);
    List<Coach> findByFirstNameLikeAndLastNameLike(String firstName, String lastName);
    Coach create(CoachRequest coachRequest);
    Coach update(Integer id, CoachRequest coachRequest);

}
