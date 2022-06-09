package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.PlayerAchievement;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.PlayerAchievementDTO;
import com.axonactive.basketball.services.impl.PlayerAchievementServiceImpl;
import com.axonactive.basketball.services.mappers.PlayerAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(PlayerAchievementResources.PATH)
public class PlayerAchievementResources {
    public static final String PATH = "/api/playerAchievement";
    @Autowired
    PlayerAchievementServiceImpl playerAchievementService;
    @GetMapping
    public ResponseEntity<List<PlayerAchievementDTO>> findAll(){
        return ResponseEntity.ok(PlayerAchievementMapper.INSTANCE.toDTOs(playerAchievementService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlayerAchievementDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        PlayerAchievement playerAchievement = playerAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(PlayerAchievementMapper.INSTANCE.toDTO(playerAchievement));
    }
    @PostMapping
    public ResponseEntity<PlayerAchievementDTO> create(@RequestBody PlayerAchievement playerAchievement){
        return ResponseEntity.created(URI.create(PATH + "/" + playerAchievement.getId())).body(PlayerAchievementMapper.INSTANCE.toDTO(playerAchievementService.save(playerAchievement)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PlayerAchievementDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody PlayerAchievement playerAchievementDetails) throws ResourceNotFoundException{
        PlayerAchievement playerAchievement = playerAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        playerAchievement.setAward(playerAchievementDetails.getAward());
        playerAchievement.setDateAchieved(playerAchievementDetails.getDateAchieved());
        playerAchievement.setPlayer(playerAchievementDetails.getPlayer());
        return ResponseEntity.created(URI.create(PATH + "/" + playerAchievement.getId())).body(PlayerAchievementMapper.INSTANCE.toDTO(playerAchievementService.save(playerAchievement)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        PlayerAchievement playerAchievement = playerAchievementService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        playerAchievementService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}

