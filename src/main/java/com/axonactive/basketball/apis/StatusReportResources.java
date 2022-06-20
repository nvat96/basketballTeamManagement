package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StatusReportRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Status;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.StatusReportServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.StatusReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(StatusReportResources.PATH)
public class StatusReportResources {
    public static final String PATH = "/api/statusReport";
    @Autowired
    StatusReportServiceImpl statusReportService;
    @Autowired
    PlayerServiceImpl playerService;
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<StatusReportDTO>> findAll() {
        return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTOs(statusReportService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (statusReport.isPresent())
            return ResponseEntity.ok(statusReport);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }
    @GetMapping("/findByPlayerID")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByPlayerID(@RequestParam(defaultValue = "0")Integer playerID){
        Optional<Player> player = playerService.findByID(playerID);
        List<StatusReport> lists = statusReportService.findByPlayerId(playerID);
        if (!player.isPresent())
            return ResponseEntity.ok("No player with the ID " + playerID);
        else if (lists.isEmpty())
            return ResponseEntity.ok("The player don't have any status report");
        else return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTOs(lists));
    }
    @GetMapping("/findByTeamNameAndYear")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByTeamNameAndYear(@RequestParam(defaultValue = "")String teamName,
                                                   @RequestParam(defaultValue = "2022")Integer year){
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team match with name " + teamName);
        else return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTOs(statusReportService.findByTeamNameAndYear(teamName, year)));
    }

    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody StatusReportRequest statusReportRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastName(statusReportRequest.getPlayerFirstName(), statusReportRequest.getPlayerLastName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statusReportRequest.getPlayerFirstName() + " " +  statusReportRequest.getPlayerLastName());
        else {
            StatusReport statusReport = new StatusReport(null,
                    Status.valueOf(statusReportRequest.getStatus()),
                    statusReportRequest.getDateInjured(),
                    statusReportRequest.getDateRecovered(),
                    statusReportRequest.getComment(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + statusReport.getId())).body(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport)));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StatusReportRequest statusReportRequest) {
        Optional<Player> player = playerService.findByFirstNameAndLastName(statusReportRequest.getPlayerFirstName(), statusReportRequest.getPlayerLastName());
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statusReportRequest.getPlayerFirstName() + " " + statusReportRequest.getPlayerLastName());
        else if (statusReport.isPresent()) {
            statusReport.get().setStatus(Status.valueOf(statusReportRequest.getStatus()));
            statusReport.get().setDateInjured(statusReportRequest.getDateInjured());
            statusReport.get().setDateRecovered(statusReportRequest.getDateRecovered());
            statusReport.get().setComment(statusReportRequest.getComment());
            statusReport.get().setPlayer(player.get());
            return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (statusReport.isPresent()) {
            statusReportService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }
}
