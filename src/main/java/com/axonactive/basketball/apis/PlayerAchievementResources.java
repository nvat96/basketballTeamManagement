package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.PlayerAchievementRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.PlayerAchievement;
import com.axonactive.basketball.enums.Award;
import com.axonactive.basketball.services.dtos.PlayerAchievementDTO;
import com.axonactive.basketball.services.impl.PlayerAchievementServiceImpl;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PlayerAchievementResources.PATH)
public class PlayerAchievementResources {
    public static final String PATH = "/api/playerAchievement";
    @Autowired
    PlayerAchievementServiceImpl playerAchievementService;
    @Autowired
    PlayerServiceImpl playerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<PlayerAchievementDTO>> findAll() {
        return ResponseEntity.ok(PlayerAchievementMapper.INSTANCE.toDTOs(playerAchievementService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerAchievement> playerAchievement = playerAchievementService.findByID(id);
        if (playerAchievement.isPresent())
            return ResponseEntity.ok(playerAchievement);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player achievement ID not found: " + id);
    }

    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody PlayerAchievementRequest playerAchievementRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(playerAchievementRequest.getPlayerFirstName(), playerAchievementRequest.getPlayerLastName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerAchievementRequest.getPlayerFirstName() + " " + playerAchievementRequest.getPlayerLastName());
        else {
            PlayerAchievement playerAchievement = new PlayerAchievement(null,
                    Award.valueOf(playerAchievementRequest.getAward()),
                    playerAchievementRequest.getDateAchieved(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + playerAchievement.getId())).body(PlayerAchievementMapper.INSTANCE.toDTO(playerAchievementService.save(playerAchievement)));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                                        @RequestBody PlayerAchievementRequest playerAchievementRequest) {
        Optional<PlayerAchievement> playerAchievement = playerAchievementService.findByID(id);
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(playerAchievementRequest.getPlayerFirstName(), playerAchievementRequest.getPlayerLastName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerAchievementRequest.getPlayerFirstName() + " " + playerAchievementRequest.getPlayerLastName());
        else if (!playerAchievement.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player achievement ID not found: " + id);
        else {
            playerAchievement.get().setAward(Award.valueOf(playerAchievementRequest.getAward()));
            playerAchievement.get().setDateAchieved(playerAchievementRequest.getDateAchieved());
            playerAchievement.get().setPlayer(player.get());
            return ResponseEntity.ok(PlayerAchievementMapper.INSTANCE.toDTO(playerAchievement.get()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerAchievement> playerAchievement = playerAchievementService.findByID(id);
        if (playerAchievement.isPresent()) {
            playerAchievementService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player achievement ID not found: " + id);
    }
}

