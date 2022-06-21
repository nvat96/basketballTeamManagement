package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.PlayerRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.enums.TypeOfPlayer;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<PlayerDTO>> findAll() {
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTOs(playerService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent())
            return ResponseEntity.ok(player);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }
    @GetMapping("/findPlayerInTheTeam")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findPlayerInTheTeam(@RequestParam(defaultValue = "") String teamName, @RequestParam(defaultValue = "2022") Integer year){
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team has name like " + teamName);
        else return ResponseEntity.ok(playerService.findPlayerThatPlayForThatTeamAtThatYear(year,teamName));
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        List<Player> players = playerService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (players.isEmpty())
            return ResponseEntity.ok("No player match with first name " + firstName + " and last name " + lastName);
        else return ResponseEntity.ok(players);
    }
    @GetMapping("/findByPositionAndTeam")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByPositionAndTeam(@RequestParam(defaultValue = "")String position,
                                                   @RequestParam(defaultValue = "")String teamName){
        List<Team> teams = teamService.findByNameLike(teamName);
        if (!position.equals("C") && !position.equals("PG") && !position.equals("SG")&& !position.equals("SF")&& !position.equals("PF"))
            return ResponseEntity.ok("There are no position like " + position);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team has name like " + teamName);
        else return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTOs(playerService.findByPositionAndTeamName(Position.valueOf(position),teamName)));
    }
    @GetMapping("/findTallestPlayer")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findTallestPlayer(@RequestParam(defaultValue = "") String teamName,
                                               @RequestParam(defaultValue = "2022") Integer year){
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team has name like " + teamName);
        else return ResponseEntity.ok(playerService.findTallestPlayerInATeam(teamName,year));
    }
    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<PlayerDTO> create(@RequestBody PlayerRequest playerRequest) {
        Player player = new Player(null,
                playerRequest.getFirstName(),
                playerRequest.getLastName(),
                playerRequest.getDateOfBirth(),
                Gender.valueOf(playerRequest.getGender()),
                playerRequest.getNationality(),
                playerRequest.getStartedDate(),
                TypeOfPlayer.valueOf(playerRequest.getTypeOfPlayer()),
                playerRequest.getSalaryExpected(),
                playerRequest.getHeight(),
                playerRequest.getWeight());
        return ResponseEntity.created(URI.create(PATH + "/" + player.getId())).body(PlayerMapper.INSTANCE.toDTO(playerService.save(player)));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody PlayerRequest playerRequest) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent()) {
            player.get().setFirstName(playerRequest.getFirstName());
            player.get().setLastName(playerRequest.getLastName());
            player.get().setDateOfBirth(playerRequest.getDateOfBirth());
            player.get().setGender(Gender.valueOf(playerRequest.getGender()));
            player.get().setNationality(playerRequest.getNationality());
            player.get().setStartedDate(playerRequest.getStartedDate());
            player.get().setTypeOfPlayer(TypeOfPlayer.valueOf(playerRequest.getTypeOfPlayer()));
            player.get().setSalaryExpected(playerRequest.getSalaryExpected());
            player.get().setHeight(playerRequest.getHeight());
            player.get().setWeight(playerRequest.getWeight());
            return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTO(playerService.save(player.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Player> player = playerService.findByID(id);
        if (player.isPresent()) {
            playerService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player ID not found: " + id);
    }
}
