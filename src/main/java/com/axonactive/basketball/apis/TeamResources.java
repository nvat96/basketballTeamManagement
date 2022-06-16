package com.axonactive.basketball.apis;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.League;
import com.axonactive.basketball.services.dtos.TeamDTO;
import com.axonactive.basketball.services.impl.ArenaServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.TeamMapper;
import com.axonactive.basketball.apis.requests.TeamRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(TeamResources.PATH)
public class TeamResources {
    public static final String PATH = "/api/team";
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    ArenaServiceImpl arenaService;

    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAll() {
        return ResponseEntity.ok(TeamMapper.INSTANCE.toDTOs(teamService.findAll()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByID(@PathVariable(value = "name") String name) {
        Optional<Team> team = teamService.findByID(name);
        if (team.isPresent())
            return ResponseEntity.ok(team);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + name);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TeamRequest teamRequest) {
        if (teamRequest.getArenaName() == null) {
            Team team = new Team(teamRequest.getName(),
                    teamRequest.getLocation(),
                    teamRequest.getDateFound(),
                    League.valueOf(teamRequest.getLeague()),
                    teamRequest.getSalaryCap(),
                    null);
            return ResponseEntity.ok(TeamMapper.INSTANCE.toDTO(teamService.save(team)));
        } else {
            Optional<Arena> arena = arenaService.findByID(teamRequest.getArenaName());
            if (!arena.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arena name not found: " + teamRequest.getArenaName());
            else {
                Team team = new Team(teamRequest.getName(),
                        teamRequest.getLocation(),
                        teamRequest.getDateFound(),
                        League.valueOf(teamRequest.getLeague()),
                        teamRequest.getSalaryCap(),
                        arena.get()
                );
                return ResponseEntity.ok(TeamMapper.INSTANCE.toDTO(teamService.save(team)));
            }
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> update(@PathVariable(value = "name") String name,
                                    @RequestBody TeamRequest teamRequest) {
        Optional<Team> team = teamService.findByID(name);
        Optional<Arena> arena = arenaService.findByID(teamRequest.getArenaName());
        if (!arena.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arena name not found: " + teamRequest.getArenaName());
        if (team.isPresent()) {
            team.get().setArena(arena.get());
            team.get().setLocation(teamRequest.getLocation());
            team.get().setDateFound(teamRequest.getDateFound());
            team.get().setSalaryCap(teamRequest.getSalaryCap());
            team.get().setLeague(League.valueOf(teamRequest.getLeague()));
            return ResponseEntity.ok(TeamMapper.INSTANCE.toDTO(teamService.save(team.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + name);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "name") String name) {
        Optional<Team> team = teamService.findByID(name);
        if (team.isPresent()) {
            teamService.deleteByID(name);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + name);
    }
}
