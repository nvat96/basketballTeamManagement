package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.PlayerRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import com.axonactive.basketball.enums.TypeOfPlayer;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PlayerResources.PATH)
public class PlayerResources {
    public static final String PATH = "/api/player";
    @Autowired
    PlayerServiceImpl playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTOs(playerService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent())
            return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTO(player.get()));
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerRequest playerRequest) {
        Player player = new Player(null,
                playerRequest.getName(),
                playerRequest.getDateOfBirth(),
                Gender.valueOf(playerRequest.getGender()),
                Nationality.valueOf(playerRequest.getNationality()),
                playerRequest.getStartedDate(),
                TypeOfPlayer.valueOf(playerRequest.getTypeOfPlayer()),
                playerRequest.getSalaryExpected());
        return ResponseEntity.created(URI.create(PATH + "/" + player.getId())).body(PlayerMapper.INSTANCE.toDTO(playerService.save(player)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody PlayerRequest playerRequest) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent()) {
            player.get().setName(playerRequest.getName());
            player.get().setDateOfBirth(playerRequest.getDateOfBirth());
            player.get().setGender(Gender.valueOf(playerRequest.getGender()));
            player.get().setNationality(Nationality.valueOf(playerRequest.getNationality()));
            player.get().setStartedDate(playerRequest.getStartedDate());
            player.get().setTypeOfPlayer(TypeOfPlayer.valueOf(playerRequest.getTypeOfPlayer()));
            player.get().setSalaryExpected(playerRequest.getSalaryExpected());
            return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTO(playerService.save(player.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent()) {
            playerService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }
}
