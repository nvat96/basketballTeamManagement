package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.AgentRequest;
import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import com.axonactive.basketball.services.dtos.AgentDTO;
import com.axonactive.basketball.services.impl.AgentServiceImpl;
import com.axonactive.basketball.services.mappers.AgentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<AgentDTO>> findAll(){
        return ResponseEntity.ok(AgentMapper.INSTANCE.toDTOs(agentService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent())
            return ResponseEntity.ok(agent);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }
    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByName(@PathVariable(value = "name") String name){
        List<Agent> agents = agentService.findByNameLike(name);
        if (agents.size() == 0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name not found: " + name);
        else return ResponseEntity.ok(AgentMapper.INSTANCE.toDTOs(agents));
    }
    @PostMapping
    public ResponseEntity<AgentDTO> create(@RequestBody AgentRequest agentRequest){
        Agent agent = new Agent(null,
                agentRequest.getName(),
                Gender.valueOf(agentRequest.getGender()),
                Nationality.valueOf(agentRequest.getNationality()),
                agentRequest.getDateOfBirth(),
                agentRequest.getCommissionRateExpected(),
                agentRequest.getPhoneNumber(),
                agentRequest.getEmail());
        return ResponseEntity.created(URI.create(PATH + "/" + agent.getId())).body(AgentMapper.INSTANCE.toDTO(agentService.save(agent)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody AgentRequest agentRequest){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent()){
            agent.get().setName(agentRequest.getName());
            agent.get().setNationality(Nationality.valueOf(agentRequest.getNationality()));
            agent.get().setGender(Gender.valueOf(agentRequest.getGender()));
            agent.get().setDateOfBirth(agentRequest.getDateOfBirth());
            agent.get().setPhoneNumber(agentRequest.getPhoneNumber());
            agent.get().setEmail(agentRequest.getEmail());
            agent.get().setCommissionRateExpected(agentRequest.getCommissionRateExpected());
            return ResponseEntity.ok(AgentMapper.INSTANCE.toDTO(agentService.save(agent.get())));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id){
        Optional<Agent> agent = agentService.findByID(id);
        if (agent.isPresent()){
            agentService.deleteByID(id);
        return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID not found: " + id);
    }

}
