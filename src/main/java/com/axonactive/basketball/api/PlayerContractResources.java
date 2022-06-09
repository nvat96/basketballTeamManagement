package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.PlayerContract;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.PlayerContractDTO;
import com.axonactive.basketball.services.impl.PlayerContractServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PlayerContractResources.PATH)
public class PlayerContractResources {
    public static final String PATH = "/api/playerContract";
    @Autowired
    PlayerContractServiceImpl playerContractService;
    @GetMapping
    public ResponseEntity<List<PlayerContractDTO>> findAll(){
        return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTOs(playerContractService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerContractDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        PlayerContract playerContract = playerContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(PlayerContractMapper.INSTANCE.toDTO(playerContract));
    }
    @PostMapping
    public ResponseEntity<PlayerContractDTO> create(@RequestBody PlayerContract playerContract){
        return ResponseEntity.created(URI.create(PATH + "/" + playerContract.getId())).body(PlayerContractMapper.INSTANCE.toDTO(playerContractService.save(playerContract)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlayerContractDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody PlayerContract playerContractDetails) throws ResourceNotFoundException{
        PlayerContract playerContract = playerContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        playerContract.setDateCreated(playerContractDetails.getDateCreated());
        playerContract.setDateExpired(playerContractDetails.getDateExpired());
        playerContract.setBody(playerContractDetails.getBody());
        playerContract.setNumber(playerContractDetails.getNumber());
        playerContract.setSalary(playerContractDetails.getSalary());
        playerContract.setTypeOfContract(playerContractDetails.getTypeOfContract());
        playerContract.setPosition(playerContractDetails.getPosition());
        playerContract.setTeam(playerContractDetails.getTeam());
        playerContract.setPlayer(playerContractDetails.getPlayer());
        return ResponseEntity.created(URI.create(PATH + "/" + playerContract.getId())).body(PlayerContractMapper.INSTANCE.toDTO(playerContractService.save(playerContract)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        PlayerContract playerContract = playerContractService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        playerContractService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
