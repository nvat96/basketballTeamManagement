package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.CoachAchievementDTO;
import com.axonactive.basketball.services.impl.CoachAchievementServiceImpl;
import com.axonactive.basketball.services.mappers.CoachAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CoachAchievementResources.PATH)
public class CoachAchievementResources {
    public static final String PATH = "/api/coachAchievement";
    @Autowired
    CoachAchievementServiceImpl coachAchievementService;
    @GetMapping
    public ResponseEntity<List<CoachAchievementDTO>> findAll(){
        return ResponseEntity.ok(CoachAchievementMapper.INSTANCE.toDTOs(coachAchievementService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoachAchievementDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        CoachAchievement coachAchievement = coachAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(CoachAchievementMapper.INSTANCE.toDTO(coachAchievement));
    }
    @PostMapping
    public ResponseEntity<CoachAchievementDTO> create(@RequestBody CoachAchievement coachAchievement){
        return ResponseEntity.created(URI.create(PATH + "/" + coachAchievement.getId())).body(CoachAchievementMapper.INSTANCE.toDTO(coachAchievementService.save(coachAchievement)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoachAchievementDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody CoachAchievement coachAchievementDetails) throws ResourceNotFoundException{
        CoachAchievement coachAchievement = coachAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        coachAchievement.setAward(coachAchievementDetails.getAward());
        coachAchievement.setDateAchieved(coachAchievementDetails.getDateAchieved());
        coachAchievement.setCoach(coachAchievementDetails.getCoach());
        return ResponseEntity.created(URI.create(PATH + "/" + coachAchievement.getId())).body(CoachAchievementMapper.INSTANCE.toDTO(coachAchievementService.save(coachAchievement)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        CoachAchievement coachAchievement = coachAchievementService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        coachAchievementService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}

