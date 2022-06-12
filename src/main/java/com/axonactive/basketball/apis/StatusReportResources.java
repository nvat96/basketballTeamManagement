package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StatusReportRequest;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.enums.Status;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.impl.StatusReportServiceImpl;
import com.axonactive.basketball.services.mappers.StatusReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<StatusReportDTO>> findAll() {
        return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTOs(statusReportService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (statusReport.isPresent())
            return ResponseEntity.ok(statusReport);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StatusReportRequest statusReportRequest) {
        Optional<Player> player = playerService.findByName(statusReportRequest.getPlayerName());
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statusReportRequest.getPlayerName());
        else {
            StatusReport statusReport = new StatusReport(null,
                    Status.valueOf(statusReportRequest.getStatus()),
                    statusReportRequest.getDateCreated(),
                    statusReportRequest.getComment(),
                    player.get());
            return ResponseEntity.created(URI.create(PATH + "/" + statusReport.getId())).body(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport)));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StatusReportRequest statusReportRequest) {
        Optional<Player> player = playerService.findByName(statusReportRequest.getPlayerName());
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + statusReportRequest.getPlayerName());
        else if (statusReport.isPresent()) {
            statusReport.get().setStatus(Status.valueOf(statusReportRequest.getStatus()));
            statusReport.get().setDateCreated(statusReportRequest.getDateCreated());
            statusReport.get().setComment(statusReportRequest.getComment());
            statusReport.get().setPlayer(player.get());
            return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<StatusReport> statusReport = statusReportService.findByID(id);
        if (statusReport.isPresent()) {
            statusReportService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status report ID not found: " + id);
    }
}
