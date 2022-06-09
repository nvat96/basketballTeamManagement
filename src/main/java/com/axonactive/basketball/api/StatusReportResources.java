package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.StatusReport;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.StatusReportDTO;
import com.axonactive.basketball.services.impl.StatusReportServiceImpl;
import com.axonactive.basketball.services.mappers.StatusReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StatusReportResources.PATH)
public class StatusReportResources {
    public static final String PATH = "/api/statusReport";
    @Autowired
    StatusReportServiceImpl statusReportService;
    @GetMapping
    public ResponseEntity<List<StatusReportDTO>> findAll(){
        return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTOs(statusReportService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StatusReportDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        StatusReport statusReport = statusReportService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(StatusReportMapper.INSTANCE.toDTO(statusReport));
    }
    @PostMapping
    public ResponseEntity<StatusReportDTO> create(@RequestBody StatusReport statusReport){
        return ResponseEntity.created(URI.create(PATH + "/" + statusReport.getId())).body(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StatusReportDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody StatusReport statusReportDetails) throws ResourceNotFoundException{
        StatusReport statusReport = statusReportService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        statusReport.setStatus(statusReportDetails.getStatus());
        statusReport.setDateCreated(statusReportDetails.getDateCreated());
        statusReport.setComment(statusReportDetails.getComment());
        statusReport.setPlayer(statusReportDetails.getPlayer());
        return ResponseEntity.created(URI.create(PATH + "/" + statusReport.getId())).body(StatusReportMapper.INSTANCE.toDTO(statusReportService.save(statusReport)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        StatusReport statusReport = statusReportService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        statusReportService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
