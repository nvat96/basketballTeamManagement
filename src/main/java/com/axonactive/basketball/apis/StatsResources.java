package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StatsRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.StatsDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.StatsServiceImpl;
import com.axonactive.basketball.services.mappers.StatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(StatsResources.PATH)
public class StatsResources {
    public static final String PATH = "/api/stats";
    @Autowired
    StatsServiceImpl statsService;
    @Autowired
    PlayerServiceImpl playerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<StatsDTO>> findAll() {
        return ResponseEntity.ok(StatsMapper.INSTANCE.toDTOs(statsService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent())
            return ResponseEntity.ok(stats);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }

    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(statsRequest.getPlayerFirstName(), statsRequest.getPlayerLastName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statsRequest.getPlayerFirstName() + " " + statsRequest.getPlayerLastName());
        else {
            Stats stats = new Stats(null,
                    statsRequest.getGamePlayed(),
                    statsRequest.getPoints(),
                    statsRequest.getAssists(),
                    statsRequest.getSteals(),
                    statsRequest.getBlocks(),
                    statsRequest.getRebounds(),
                    statsRequest.getThreePointerPercentage(),
                    statsRequest.getFieldGoalPercentage(),
                    statsRequest.getFreeThrowPercentage(),
                    statsRequest.getSeason(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + stats.getId())).body(StatsMapper.INSTANCE.toDTO(statsService.save(stats)));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(statsRequest.getPlayerFirstName(), statsRequest.getPlayerLastName());
        Optional<Stats> stats = statsService.findByID(id);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statsRequest.getPlayerFirstName() + " " + statsRequest.getPlayerLastName());
        else if (stats.isPresent()) {
            stats.get().setGamePlayed(statsRequest.getGamePlayed());
            stats.get().setPoints(statsRequest.getPoints());
            stats.get().setAssists(statsRequest.getAssists());
            stats.get().setSteals(statsRequest.getSteals());
            stats.get().setBlocks(statsRequest.getBlocks());
            stats.get().setRebounds(statsRequest.getRebounds());
            stats.get().setThreePointerPercentage(statsRequest.getThreePointerPercentage());
            stats.get().setFieldGoalPercentage(statsRequest.getFieldGoalPercentage());
            stats.get().setFreeThrowPercentage(statsRequest.getFreeThrowPercentage());
            stats.get().setSeason(statsRequest.getSeason());
            stats.get().setPlayer(player.get());
            return ResponseEntity.ok(StatsMapper.INSTANCE.toDTO(statsService.save(stats.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent()) {
            statsService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }
}
