package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.TeamAchievementRequest;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.entities.TeamAchievement;
import com.axonactive.basketball.enums.Award;
import com.axonactive.basketball.services.dtos.TeamAchievementDTO;
import com.axonactive.basketball.services.impl.TeamAchievementServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.TeamAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(TeamAchievementResources.PATH)
public class TeamAchievementResources {
    public static final String PATH = "/api/teamAchievement";
    @Autowired
    TeamAchievementServiceImpl teamAchievementService;
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    public ResponseEntity<List<TeamAchievementDTO>> findAll() {
        return ResponseEntity.ok(TeamAchievementMapper.INSTANCE.toDTOs(teamAchievementService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<TeamAchievement> teamAchievement = teamAchievementService.findByID(id);
        if (teamAchievement.isPresent())
            return ResponseEntity.ok(teamAchievement);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team achievement ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeamAchievementRequest teamAchievementRequest) {
        Optional<Team> team = teamService.findByID(teamAchievementRequest.getTeamName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + teamAchievementRequest.getTeamName());
        else {
            TeamAchievement teamAchievement = new TeamAchievement(null,
                    teamAchievementRequest.getDateAchieved(),
                    Award.valueOf(teamAchievementRequest.getAward()),
                    team.get());
            return ResponseEntity.created(URI.create(PATH + "/" + teamAchievement.getId())).body(TeamAchievementMapper.INSTANCE.toDTO(teamAchievementService.save(teamAchievement)));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody TeamAchievementRequest teamAchievementRequest) {
        Optional<TeamAchievement> teamAchievement = teamAchievementService.findByID(id);
        Optional<Team> team = teamService.findByID(teamAchievementRequest.getTeamName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + teamAchievementRequest.getTeamName());
        else if (teamAchievement.isPresent()) {
            teamAchievement.get().setDateAchieved(teamAchievementRequest.getDateAchieved());
            teamAchievement.get().setAward(Award.valueOf(teamAchievementRequest.getAward()));
            teamAchievement.get().setTeam(team.get());
            return ResponseEntity.ok(TeamAchievementMapper.INSTANCE.toDTO(teamAchievementService.save(teamAchievement.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team achievement ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<TeamAchievement> teamAchievement = teamAchievementService.findByID(id);
        if (teamAchievement.isPresent()) {
            teamAchievementService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team achievement ID not found: " + id);
    }
}
