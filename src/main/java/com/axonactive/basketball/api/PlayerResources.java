package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.PlayerService;
import com.axonactive.basketball.services.dtos.PlayerDTO;
import com.axonactive.basketball.services.impl.PlayerServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PlayerResources.PATH)
public class PlayerResources {
    public static final String PATH = "/api/player";
    @Autowired
    PlayerServiceImpl playerService;
    @GetMapping
    public ResponseEntity<List<PlayerDTO>> findAll(){
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTOs(playerService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Player player = playerService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found: " + id));
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTO(player));
    }
    @PostMapping
    public ResponseEntity<PlayerDTO> create(@RequestBody Player player){
        return ResponseEntity.created(URI.create(PATH + "/" + player.getId())).body(PlayerMapper.INSTANCE.toDTO(playerService.save(player)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable(value = "id")Integer id,
                                            @RequestBody Player playerDetails) throws ResourceNotFoundException{
        Player player = playerService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found: " + id));
        player.setName(playerDetails.getName());
        player.setDateOfBirth(playerDetails.getDateOfBirth());
        player.setGender(playerDetails.getGender());
        player.setNationality(playerDetails.getNationality());
        player.setStartedDate(playerDetails.getStartedDate());
        player.setTypeOfPlayer(playerDetails.getTypeOfPlayer());
        return ResponseEntity.ok(PlayerMapper.INSTANCE.toDTO(playerService.save(player)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Player player = playerService.findByID(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found: " + id));
        playerService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }
}
