package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.CoachAchievementRequest;
import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.entities.CoachAchievement;
import com.axonactive.basketball.enums.Award;
import com.axonactive.basketball.services.dtos.CoachAchievementDTO;
import com.axonactive.basketball.services.impl.CoachAchievementServiceImpl;
import com.axonactive.basketball.services.impl.CoachServiceImpl;
import com.axonactive.basketball.services.mappers.CoachAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CoachAchievementResources.PATH)
public class CoachAchievementResources {
    public static final String PATH = "/api/coachAchievement";
    @Autowired
    CoachAchievementServiceImpl coachAchievementService;
    @Autowired
    CoachServiceImpl coachService;

    @GetMapping
    public ResponseEntity<List<CoachAchievementDTO>> findAll() {
        return ResponseEntity.ok(CoachAchievementMapper.INSTANCE.toDTOs(coachAchievementService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<CoachAchievement> coachAchievement = coachAchievementService.findByID(id);
        if (coachAchievement.isPresent())
            return ResponseEntity.ok(coachAchievement);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach achievement ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CoachAchievementRequest coachAchievementRequest) {
        Optional<Coach> coach = coachService.findByName(coachAchievementRequest.getCoachName());
        if (coach.isPresent()) {
            CoachAchievement coachAchievement = new CoachAchievement(null,
                    Award.valueOf(coachAchievementRequest.getAward()),
                    coachAchievementRequest.getDateAchieved(),
                    coach.get());
            return ResponseEntity.created(URI.create(PATH + "/" + coachAchievement.getId())).body(CoachAchievementMapper.INSTANCE.toDTO(coachAchievementService.save(coachAchievement)));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach name not found: " + coachAchievementRequest.getCoachName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                                    @RequestBody CoachAchievementRequest coachAchievementRequest) {
        Optional<Coach> coach = coachService.findByName(coachAchievementRequest.getCoachName());
        Optional<CoachAchievement> coachAchievement = coachAchievementService.findByID(id);
        if (!coach.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach name not found: " + coachAchievementRequest.getCoachName());
        else if (coachAchievement.isPresent()) {
            coachAchievement.get().setAward(Award.valueOf(coachAchievementRequest.getAward()));
            coachAchievement.get().setDateAchieved(coachAchievementRequest.getDateAchieved());
            coachAchievement.get().setCoach(coach.get());
            return ResponseEntity.ok(CoachAchievementMapper.INSTANCE.toDTO(coachAchievementService.save(coachAchievement.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach achievement ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<CoachAchievement> coachAchievement = coachAchievementService.findByID(id);
        if (coachAchievement.isPresent()) {
            coachAchievementService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach achievement ID not found: " + id);
    }
}

