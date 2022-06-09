package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.AgentContract;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.AgentContractDTO;
import com.axonactive.basketball.services.impl.AgentContractServiceImpl;
import com.axonactive.basketball.services.mappers.AgentContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AgentContractResources.PATH)
public class AgentContractResources {
    public static final String PATH = "/api/agentContract";
    @Autowired
    AgentContractServiceImpl agentContractService;
    @GetMapping
    public ResponseEntity<List<AgentContractDTO>> findAll(){
        return ResponseEntity.ok(AgentContractMapper.INSTANCE.toDTOs(agentContractService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgentContractDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        AgentContract agentContract = agentContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(AgentContractMapper.INSTANCE.toDTO(agentContract));
    }
    @PostMapping
    public ResponseEntity<AgentContractDTO> create(@RequestBody AgentContract agentContract){
        return ResponseEntity.created(URI.create(PATH + "/" + agentContract.getId())).body(AgentContractMapper.INSTANCE.toDTO(agentContractService.save(agentContract)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AgentContractDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody AgentContract agentContractDetails) throws ResourceNotFoundException{
        AgentContract agentContract = agentContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));

        return ResponseEntity.created(URI.create(PATH + "/" + agentContract.getId())).body(AgentContractMapper.INSTANCE.toDTO(agentContractService.save(agentContract)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        AgentContract agentContract = agentContractService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        agentContractService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
