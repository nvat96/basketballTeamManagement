package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.PlayerContractRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.enums.TypeOfContract;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import com.axonactive.basketball.services.dtos.PlayerWithContractDTO;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.impl.PlayerContractServiceImpl;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    OwnerServiceImpl ownerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<PlayerContractDTO>> findAll() {
        return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTOs(playerContractService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        if (playerContract.isPresent())
            return ResponseEntity.ok(playerContract);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }

    @GetMapping("/findByPlayerID")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByPlayerID(@RequestParam(defaultValue = "0") Integer playerID) {
        Optional<Player> player = playerService.findByID(playerID);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + playerID);
        else
            return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTOs(playerContractService.findByPlayerId(playerID)));
    }

    @GetMapping("/findByTeamName")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByTeamName(@RequestParam(defaultValue = "") String teamName) {
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No team name match with " + teamName);
        else
            return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTOs(playerContractService.findByTeamNameLike(teamName)));
    }

    @GetMapping("/findContractExpired")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findContractExpired(@RequestParam(defaultValue = "2022") Integer year) {
        List<PlayerWithContractDTO> list = playerContractService.findPlayerWithContractThatExpiredInThatYear(year);
        if (list.isEmpty())
            return ResponseEntity.ok("No contract expired in " + year);
        else return ResponseEntity.ok(list);
    }

    @GetMapping("/findContractCreated")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findContractCreated(@RequestParam(defaultValue = "2022") Integer year) {
        List<PlayerWithContractDTO> list = playerContractService.findPlayerWithContractThatCreatedInThatYear(year);
        if (list.isEmpty())
            return ResponseEntity.ok("No contract created in " + year);
        else return ResponseEntity.ok(list);
    }

    @GetMapping("/findContractActive")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findContractActive(@RequestParam(defaultValue = "") String teamName) {
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team name match with " + teamName);
        else return ResponseEntity.ok(playerContractService.findPlayerContractThatAreActiveOfATeam(teamName));
    }
    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody PlayerContractRequest playerContractRequest) {
        Optional<Team> team = teamService.findByID(playerContractRequest.getTeamName());
        Optional<Player> player = playerService.findByFirstNameAndLastName(playerContractRequest.getPlayerFirstName(), playerContractRequest.getPlayerLastName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + playerContractRequest.getTeamName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerContractRequest.getPlayerFirstName() + " " + playerContractRequest.getPlayerLastName());
        else {
            Double salaryMustPay = ownerService.calculateSalaryMustPay(playerContractRequest.getTeamName()) + playerContractRequest.getSalary();
            if (teamService.isSalaryMustPayOverSalaryCap(playerContractRequest.getTeamName(), salaryMustPay))
                return ResponseEntity.ok("The salary of the contract you about to create is above the salary cap of the team " + playerContractRequest.getTeamName() +
                        "\nTeam " + playerContractRequest.getTeamName() +  " salary must pay: $" + salaryMustPay + "m/year" +
                        "\nTeam " + playerContractRequest.getTeamName() + "'s salary cap: $" + team.get().getSalaryCap() + "m/year");
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
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody PlayerContractRequest playerContractRequest) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        Optional<Team> team = teamService.findByID(playerContractRequest.getTeamName());
        Optional<Player> player = playerService.findByFirstNameAndLastName(playerContractRequest.getPlayerFirstName(), playerContractRequest.getPlayerLastName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + playerContractRequest.getTeamName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + playerContractRequest.getPlayerFirstName() + " " + playerContractRequest.getPlayerLastName());
        else if (playerContract.isPresent()) {
            Double salaryMustPay = ownerService.calculateSalaryMustPay(playerContractRequest.getTeamName()) + playerContractRequest.getSalary() - playerContract.get().getSalary();
            if (teamService.isSalaryMustPayOverSalaryCap(playerContractRequest.getTeamName(), salaryMustPay))
                return ResponseEntity.ok("The salary of the contract you about to update is above the salary cap of the team " + playerContractRequest.getTeamName() +
                        "\nTeam " + playerContractRequest.getTeamName() +  " salary must pay: $" + salaryMustPay + "m/year" +
                        "\nTeam " + playerContractRequest.getTeamName() + "'s salary cap: $" + team.get().getSalaryCap() + "m/year");
            else {
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
            }
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<PlayerContract> playerContract = playerContractService.findByID(id);
        if (playerContract.isPresent()) {
            playerContractService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player contract ID not found: " + id);
    }
}
