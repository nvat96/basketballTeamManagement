package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.CoachContractDTO;
import com.axonactive.basketball.services.impl.CoachContractServiceImpl;
import com.axonactive.basketball.services.mappers.CoachContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(CoachContractResources.PATH)
public class CoachContractResources {
    public static final String PATH = "/api/coachContract";
    @Autowired
    CoachContractServiceImpl coachContractService;
    @GetMapping
    public ResponseEntity<List<CoachContractDTO>> findAll(){
        return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTOs(coachContractService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CoachContractDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTO(coachContract));
    }
    @PostMapping
    public ResponseEntity<CoachContractDTO> create(@RequestBody CoachContract coachContract){
        return ResponseEntity.created(URI.create(PATH + "/" + coachContract.getId())).body(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CoachContractDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody CoachContract coachContractDetails) throws ResourceNotFoundException{
        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        coachContractDetails.setDateCreated(coachContractDetails.getDateCreated());
        coachContractDetails.setDateExpired(coachContractDetails.getDateExpired());
        coachContractDetails.setTitle(coachContractDetails.getTitle());
        coachContractDetails.setSalary(coachContractDetails.getSalary());
        coachContractDetails.setTypeOfContract(coachContractDetails.getTypeOfContract());
        coachContractDetails.setBody(coachContractDetails.getBody());
        coachContractDetails.setCoach(coachContractDetails.getCoach());
        coachContractDetails.setTeam(coachContractDetails.getTeam());
        return ResponseEntity.created(URI.create(PATH + "/" + coachContract.getId())).body(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        coachContractService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
