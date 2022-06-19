package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.AgentRequest;
import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.services.dtos.AgentDTO;
import com.axonactive.basketball.services.impl.AgentServiceImpl;
import com.axonactive.basketball.services.mappers.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AgentResources.PATH)
public class AgentResources {
    public static final String PATH = "/api/agent";
    @Autowired
    AgentServiceImpl agentService;
    @GetMapping
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<List<AgentDTO>> findAll(){
        return ResponseEntity.ok(AgentMapper.INSTANCE.toDTOs(agentService.findAll()));
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent())
            return ResponseEntity.ok(agent);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('HIGH_MANAGEMENT', 'USER')")
    public ResponseEntity<?> findAgentByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        List<Agent> agents = agentService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (agents.isEmpty())
            return ResponseEntity.ok("No agent match with first name like " + firstName + " and last name like " + lastName);
        else return ResponseEntity.ok(agents);
    }
    @PostMapping
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<AgentDTO> create(@RequestBody AgentRequest agentRequest){
        Agent agent = new Agent(null,
                agentRequest.getFirstName(),
                agentRequest.getLastName(),
                Gender.valueOf(agentRequest.getGender()),
                agentRequest.getNationality(),
                agentRequest.getDateOfBirth(),
                agentRequest.getPhoneNumber(),
                agentRequest.getEmail());
        return ResponseEntity.created(URI.create(PATH + "/" + agent.getId())).body(AgentMapper.INSTANCE.toDTO(agentService.save(agent)));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody AgentRequest agentRequest){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent()){
            agent.get().setFirstName(agentRequest.getFirstName());
            agent.get().setLastName(agentRequest.getLastName());
            agent.get().setNationality(agentRequest.getNationality());
            agent.get().setGender(Gender.valueOf(agentRequest.getGender()));
            agent.get().setDateOfBirth(agentRequest.getDateOfBirth());
            agent.get().setPhoneNumber(agentRequest.getPhoneNumber());
            agent.get().setEmail(agentRequest.getEmail());
            return ResponseEntity.ok(AgentMapper.INSTANCE.toDTO(agentService.save(agent.get())));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('HIGH_MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent()){
            agentService.deleteByID(id);
        return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }

}
