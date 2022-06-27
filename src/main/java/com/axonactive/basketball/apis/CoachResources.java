package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.CoachRequest;
import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.exceptions.ExceptionList;
import com.axonactive.basketball.services.dtos.CoachDTO;
import com.axonactive.basketball.services.impl.CoachServiceImpl;
import com.axonactive.basketball.services.mappers.CoachMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CoachResources.PATH)
@Slf4j
public class CoachResources {
    public static final String PATH = "/api/coach";
    @Autowired
    CoachServiceImpl coachService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<CoachDTO>> findAll() {
        return ResponseEntity.ok(CoachMapper.INSTANCE.toDTOs(coachService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        try{
            return ResponseEntity.ok(coachService.findByID(id));
        }catch (Exception e){
            log.error("Find coach by id {}",id);
            throw ExceptionList.coachNotFound();
        }
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findCoachByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        try{
            return ResponseEntity.ok(coachService.findByFirstNameLikeAndLastNameLike(firstName, lastName));
        }catch (Exception e){
            log.error("Find coach by first name and last name {}{}",firstName,lastName);
            throw ExceptionList.badRequest("Not Found","No coach with first name and last name match");
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<CoachDTO> create(@RequestBody @Valid CoachRequest coachRequest) {
        Coach coach = coachService.create(coachRequest);
        return ResponseEntity.created(URI.create(PATH + "/" + coach.getId())).body(CoachMapper.INSTANCE.toDTO(coachService.save(coach)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<CoachDTO> update(@PathVariable(value = "id") Integer id,
                                                        @RequestBody @Valid CoachRequest coachRequest) {
        try {
            return ResponseEntity.ok(CoachMapper.INSTANCE.toDTO(coachService.update(id,coachRequest)));
        }catch (Exception e){
            log.error("Find coach by ID {}",id);
            throw ExceptionList.coachNotFound();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") Integer id) {
        try {
            coachService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }catch (Exception e){
            log.error("Find coach by ID {}",id);
            throw ExceptionList.coachNotFound();
        }
    }

}