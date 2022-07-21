package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.AgentRequest;
import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.exceptions.ExceptionList;
import com.axonactive.basketball.services.dtos.AgentDTO;
import com.axonactive.basketball.services.impl.AgentServiceImpl;
import com.axonactive.basketball.services.mappers.AgentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(AgentResources.PATH)
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class AgentResources {
    public static final String PATH = "/api/agent";
    @Autowired
    AgentServiceImpl agentService;

    @GetMapping
//    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<AgentDTO>> findAll() {
        log.error("Test log");
        return ResponseEntity.ok(AgentMapper.INSTANCE.toDTOs(agentService.findAll()));
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<Agent> findByID(@PathVariable(value = "id") Integer id) {
        try {
            return ResponseEntity.ok(agentService.findByID(id).get());
        } catch (Exception e) {
            log.error("Find agent by ID {}", id);
            throw ExceptionList.agentNotFound();
        }
    }

    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findAgentByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName) {
        List<Agent> agents = agentService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (agents.isEmpty())
            return ResponseEntity.ok("No agent match with first name " + firstName + " and last name " + lastName);
        else return ResponseEntity.ok(agents);
    }

    @PostMapping
//    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<AgentDTO> create(@RequestBody AgentRequest agentRequest) {
        Agent agent = agentService.create(agentRequest);
        return ResponseEntity.created(URI.create(PATH + "/" + agent.getId())).body(AgentMapper.INSTANCE.toDTO(agentService.save(agent)));
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<AgentDTO> update(@PathVariable(value = "id") Integer id, @RequestBody AgentRequest agentRequest) {
        try {
            Agent agent = agentService.update(id,agentRequest);
            return ResponseEntity.ok(AgentMapper.INSTANCE.toDTO(agent));
        }catch (Exception e){
            log.error("Find agent by ID {}",id);
            throw ExceptionList.agentNotFound();
        }
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<String> deleteByID(@PathVariable(value = "id") Integer id) {
        try {
            agentService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }catch (Exception e){
            log.error("Find agent by ID {}",id);
            throw ExceptionList.agentNotFound();
        }
    }
}
