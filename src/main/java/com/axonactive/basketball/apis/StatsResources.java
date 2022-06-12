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
    public ResponseEntity<List<StatsDTO>> findAll() {
        return ResponseEntity.ok(StatsMapper.INSTANCE.toDTOs(statsService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent())
            return ResponseEntity.ok(stats);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByName(statsRequest.getPlayerName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statsRequest.getPlayerName());
        else {
            Stats stats = new Stats(null,
                    statsRequest.getHeight(),
                    statsRequest.getWeight(),
                    statsRequest.getTotalTwoPointFG(),
                    statsRequest.getTwoPointFGMade(),
                    statsRequest.getTwoPointFGPercentage(),
                    statsRequest.getTotalThreePointFG(),
                    statsRequest.getThreePointFGMade(),
                    statsRequest.getThreePointFGPercentage(),
                    statsRequest.getTotalFreeThrow(),
                    statsRequest.getFreeThrowMade(),
                    statsRequest.getFreeThrowPercentage(),
                    statsRequest.getSteal(),
                    statsRequest.getBlock(),
                    statsRequest.getRebound(),
                    statsRequest.getFoul(),
                    statsRequest.getTurnover(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + stats.getId())).body(StatsMapper.INSTANCE.toDTO(statsService.save(stats)));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StatsRequest statsRequest) {
        Optional<Player> player = playerService.findByName(statsRequest.getPlayerName());
        Optional<Stats> stats = statsService.findByID(id);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statsRequest.getPlayerName());
        else if (stats.isPresent()) {
            stats.get().setHeight(statsRequest.getHeight());
            stats.get().setWeight(statsRequest.getWeight());
            stats.get().setTotalTwoPointFG(statsRequest.getTotalTwoPointFG());
            stats.get().setTwoPointFGMade(statsRequest.getTwoPointFGMade());
            stats.get().setTotalThreePointFG(statsRequest.getTotalThreePointFG());
            stats.get().setThreePointFGMade(statsRequest.getThreePointFGMade());
            stats.get().setTotalFreeThrow(statsRequest.getTotalFreeThrow());
            stats.get().setFreeThrowMade(statsRequest.getFreeThrowMade());
            stats.get().setSteal(statsRequest.getSteal());
            stats.get().setBlock(statsRequest.getBlock());
            stats.get().setRebound(statsRequest.getRebound());
            stats.get().setFoul(statsRequest.getFoul());
            stats.get().setTurnover(statsRequest.getTurnover());
            stats.get().setFreeThrowPercentage();
            stats.get().setThreePointPercentage();
            stats.get().setTwoPointPercentage();
            return ResponseEntity.ok(StatsMapper.INSTANCE.toDTO(statsService.save(stats.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Stats> stats = statsService.findByID(id);
        if (stats.isPresent()) {
            statsService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stats ID not found: " + id);
    }
}
