package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StatsRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.services.dtos.PlayerWithStatsDTO;
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
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<StatsDTO>> findAll() {
        return ResponseEntity.ok(StatsMapper.INSTANCE.toDTOs(statsService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent())
            return ResponseEntity.ok(stats);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }
    @GetMapping("/findByPlayerID")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByPlayerID(@RequestParam(defaultValue = "0") Integer playerID){
        Optional<Player> player = playerService.findByID(playerID);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + playerID);
        else return ResponseEntity.ok(StatsMapper.INSTANCE.toDTOs(statsService.findByPlayerId(playerID)));
    }
    @GetMapping("/findPlayerWithStats")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findPlayerWithStats(@RequestParam(defaultValue = "0") Integer playerID,
                                                                        @RequestParam(defaultValue = "2022") Integer season){
        PlayerWithStatsDTO player = statsService.findPlayerWithStatsInASeason(playerID,season);
        if (player == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + playerID +
                    "\nOr player didn't play in season " + season);
        else return ResponseEntity.ok(player);
    }
    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastName(statsRequest.getPlayerFirstName(), statsRequest.getPlayerLastName());
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
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastName(statsRequest.getPlayerFirstName(), statsRequest.getPlayerLastName());
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
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent()) {
            statsService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }
}
