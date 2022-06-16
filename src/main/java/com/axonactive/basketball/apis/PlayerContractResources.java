package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.PlayerContractRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.enums.TypeOfContract;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import com.axonactive.basketball.services.impl.PlayerContractServiceImpl;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(PlayerContractResources.PATH)
public class PlayerContractResources {
    public static final String PATH = "/api/playerContract";
    @Autowired
    PlayerContractServiceImpl playerContractService;
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    PlayerServiceImpl playerService;

    @GetMapping
    public ResponseEntity<List<PlayerContractDTO>> findAll() {
        return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTOs(playerContractService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        if (playerContract.isPresent())
            return ResponseEntity.ok(playerContract);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PlayerContractRequest playerContractRequest) {
        Optional<Team> team = teamService.findByID(playerContractRequest.getTeamName());
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(playerContractRequest.getPlayerFirstName(), playerContractRequest.getPlayerLastName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + playerContractRequest.getTeamName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerContractRequest.getPlayerFirstName() + " " + playerContractRequest.getPlayerLastName());
        else {
            PlayerContract playerContract = new PlayerContract(null,
                    playerContractRequest.getDateCreated(),
                    playerContractRequest.getDateExpired(),
                    playerContractRequest.getBody(),
                    playerContractRequest.getNumber(),
                    playerContractRequest.getSalary(),
                    TypeOfContract.valueOf(playerContractRequest.getTypeOfContract()),
                    Position.valueOf(playerContractRequest.getPosition()),
                    team.get(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + playerContract.getId())).body(PlayerContractMapper.INSTANCE.toDTO(playerContractService.save(playerContract)));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody PlayerContractRequest playerContractRequest) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        Optional<Team> team = teamService.findByID(playerContractRequest.getTeamName());
        Optional<Player> player = playerService.findByFirstNameAndLastNameLike(playerContractRequest.getPlayerFirstName(), playerContractRequest.getPlayerLastName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + playerContractRequest.getTeamName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerContractRequest.getPlayerFirstName() + " " + playerContractRequest.getPlayerLastName());
        else if (playerContract.isPresent()) {
            playerContract.get().setDateCreated(playerContractRequest.getDateCreated());
            playerContract.get().setDateExpired(playerContractRequest.getDateExpired());
            playerContract.get().setBody(playerContractRequest.getBody());
            playerContract.get().setNumber(playerContractRequest.getNumber());
            playerContract.get().setSalary(playerContractRequest.getSalary());
            playerContract.get().setTypeOfContract(TypeOfContract.valueOf(playerContractRequest.getTypeOfContract()));
            playerContract.get().setPosition(Position.valueOf(playerContractRequest.getPosition()));
            playerContract.get().setTeam(team.get());
            playerContract.get().setPlayer(player.get());
            return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTO(playerContractService.save(playerContract.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        if (playerContract.isPresent()) {
            playerContractService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }
}
