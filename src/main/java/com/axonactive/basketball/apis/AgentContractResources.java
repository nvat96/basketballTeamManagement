package com.axonactive.basketball.apis;

import com.axonactive.basketball.entities.Agent;
import com.axonactive.basketball.entities.AgentContract;
import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.services.dtos.AgentContractDTO;
import com.axonactive.basketball.services.impl.AgentContractServiceImpl;
import com.axonactive.basketball.services.impl.AgentServiceImpl;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.mappers.AgentContractMapper;
import com.axonactive.basketball.apis.requests.AgentContractRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(AgentContractResources.PATH)
public class AgentContractResources {
    public static final String PATH = "/api/agentContract";
    @Autowired
    AgentContractServiceImpl agentContractService;
    @Autowired
    AgentServiceImpl agentService;
    @Autowired
    PlayerServiceImpl playerService;
    @GetMapping
    public ResponseEntity<List<AgentContractDTO>> findAll(){
        return ResponseEntity.ok(AgentContractMapper.INSTANCE.toDTOs(agentContractService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id){
        Optional<AgentContract> agentContract = agentContractService.findByID(id);
        if (agentContract.isPresent())
            return ResponseEntity.ok(agentContract);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent contract ID not found: " + id);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody AgentContractRequest agentContractRequest){
        Optional<Agent> agent = agentService.findByName(agentContractRequest.getAgentName());
        Optional<Player> player = playerService.findByName(agentContractRequest.getPlayerName());
        if (!agent.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent name not found: " + agentContractRequest.getAgentName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + agentContractRequest.getPlayerName());
        else {
            AgentContract agentContract = new AgentContract(null,
                    agentContractRequest.getDateCreated(),
                    agentContractRequest.getDateExpired(),
                    agentContractRequest.getCommissionRate(),
                    agentContractRequest.getBody(),
                    player.get(),
                    agent.get());
            return ResponseEntity.created(URI.create(PATH + "/" + agentContract.getId())).body(AgentContractMapper.INSTANCE.toDTO(agentContractService.save(agentContract)));
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody AgentContractRequest agentContractRequest){
        Optional<Agent> agent = agentService.findByName(agentContractRequest.getAgentName());
        Optional<Player> player = playerService.findByName(agentContractRequest.getPlayerName());
        Optional<AgentContract> agentContract = agentContractService.findByID(id);
        if (!agent.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent name not found: " + agentContractRequest.getAgentName());
        else if (!player.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Player name not found: " + agentContractRequest.getPlayerName());
        else if(agentContract.isPresent()){
            agentContract.get().setDateCreated(agentContractRequest.getDateCreated());
            agentContract.get().setDateExpired(agentContractRequest.getDateExpired());
            agentContract.get().setBody(agentContractRequest.getBody());
            agentContract.get().setCommissionRate(agentContractRequest.getCommissionRate());
            agentContract.get().setAgent(agent.get());
            agentContract.get().setPlayer(player.get());
            return ResponseEntity.ok(AgentContractMapper.INSTANCE.toDTO(agentContractService.save(agentContract.get())));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent contract ID not found: " + id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id){
        Optional<AgentContract> agentContract = agentContractService.findByID(id);
        if (agentContract.isPresent()){
            agentContractService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Agent contract ID not found: " + id);
    }
}
