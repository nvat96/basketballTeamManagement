package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.AgentDTO;
import com.axonactive.basketball.services.impl.AgentServiceImpl;
import com.axonactive.basketball.services.mappers.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AgentResources.PATH)
public class AgentResources {
    public static final String PATH = "/api/agent";
    @Autowired
    AgentServiceImpl agentService;
    @GetMapping
    public ResponseEntity<List<AgentDTO>> findAll(){
        return ResponseEntity.ok(AgentMapper.INSTANCE.toDTOs(agentService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgentDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Agent agent = agentService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(AgentMapper.INSTANCE.toDTO(agent));
    }
    @PostMapping
    public ResponseEntity<AgentDTO> create(@RequestBody Agent agent){
        return ResponseEntity.created(URI.create(PATH + "/" + agent.getId())).body(AgentMapper.INSTANCE.toDTO(agentService.save(agent)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<AgentDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Agent agentDetails) throws ResourceNotFoundException{
        Agent agent = agentService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        agent.setName(agentDetails.getName());
        agent.setNationality(agentDetails.getNationality());
        agent.setGender(agentDetails.getGender());
        agent.setDateOfBirth(agentDetails.getDateOfBirth());
        return ResponseEntity.created(URI.create(PATH + "/" + agent.getId())).body(AgentMapper.INSTANCE.toDTO(agentService.save(agent)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Agent agent = agentService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        agentService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
