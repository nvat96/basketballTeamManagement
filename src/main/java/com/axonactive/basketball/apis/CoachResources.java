package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.CoachRequest;
import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.services.dtos.CoachDTO;
import com.axonactive.basketball.services.impl.CoachServiceImpl;
import com.axonactive.basketball.services.mappers.CoachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CoachResources.PATH)
public class CoachResources {
    public static final String PATH = "/api/coach";
    @Autowired
    CoachServiceImpl coachService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<CoachDTO>> findAll() {
        return ResponseEntity.ok(CoachMapper.INSTANCE.toDTOs(coachService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Coach> coach = coachService.findByID(id);
        if (coach.isPresent())
            return ResponseEntity.ok(coach);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach ID not found: " + id);
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findCoachByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        List<Coach> coaches = coachService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (coaches.isEmpty())
            return ResponseEntity.ok("No coach match with first name like " + firstName + " and last name like " + lastName);
        else return ResponseEntity.ok(coaches);
    }

    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<CoachDTO> create(@RequestBody CoachRequest coachRequest) {
        Coach coach = new Coach(null,
                coachRequest.getFirstName(),
                coachRequest.getLastName(),
                coachRequest.getDateOfBirth(),
                Gender.valueOf(coachRequest.getGender()),
                coachRequest.getNationality(),
                coachRequest.getDateStarted(),
                coachRequest.getSalaryExpected());
        return ResponseEntity.created(URI.create(PATH + "/" + coach.getId())).body(CoachMapper.INSTANCE.toDTO(coachService.save(coach)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                                        @RequestBody CoachRequest coachRequest) {
        Optional<Coach> coach = coachService.findByID(id);
        if (coach.isPresent()) {
            coach.get().setFirstName(coachRequest.getFirstName());
            coach.get().setLastName(coachRequest.getLastName());
            coach.get().setDateOfBirth(coachRequest.getDateOfBirth());
            coach.get().setGender(Gender.valueOf(coachRequest.getGender()));
            coach.get().setNationality(coachRequest.getNationality());
            coach.get().setDateStarted(coachRequest.getDateStarted());
            coach.get().setSalaryExpected(coachRequest.getSalaryExpected());
            return ResponseEntity.ok(CoachMapper.INSTANCE.toDTO(coachService.save(coach.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Coach> coach = coachService.findByID(id);
        if (coach.isPresent()) {
            coachService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach ID not found: " + id);
    }

}