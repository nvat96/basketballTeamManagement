package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.CoachDTO;
import com.axonactive.basketball.services.impl.CoachServiceImpl;
import com.axonactive.basketball.services.mappers.CoachMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CoachResources.PATH)
public class CoachResources {
    public static final String PATH = "/api/coach";
    @Autowired
    CoachServiceImpl coachService;
    @GetMapping
    public ResponseEntity<List<CoachDTO>> findAll(){
        return ResponseEntity.ok(CoachMapper.INSTANCE.toDTOs(coachService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoachDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Coach coach = coachService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(CoachMapper.INSTANCE.toDTO(coach));
    }
    @PostMapping
    public ResponseEntity<CoachDTO> create(@RequestBody Coach coach){
        return ResponseEntity.created(URI.create(PATH + "/" + coach.getId())).body(CoachMapper.INSTANCE.toDTO(coachService.save(coach)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoachDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Coach coachDetails) throws ResourceNotFoundException{
        Coach coach = coachService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        coach.setName(coachDetails.getName());
        coach.setNationality(coachDetails.getNationality());
        coach.setGender(coachDetails.getGender());
        coach.setDateOfBirth(coachDetails.getDateOfBirth());
        coach.setDateStarted(coachDetails.getDateStarted());
        return ResponseEntity.created(URI.create(PATH + "/" + coach.getId())).body(CoachMapper.INSTANCE.toDTO(coachService.save(coach)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Coach coach = coachService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        coachService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}