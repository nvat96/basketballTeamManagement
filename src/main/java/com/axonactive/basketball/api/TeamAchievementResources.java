package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.TeamAchievement;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.TeamAchievementDTO;
import com.axonactive.basketball.services.impl.TeamAchievementServiceImpl;
import com.axonactive.basketball.services.mappers.TeamAchievementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TeamAchievementResources.PATH)
public class TeamAchievementResources {
    public static final String PATH = "/api/teamAchievement";
    @Autowired
    TeamAchievementServiceImpl teamAchievementService;
    @GetMapping
    public ResponseEntity<List<TeamAchievementDTO>> findAll(){
        return ResponseEntity.ok(TeamAchievementMapper.INSTANCE.toDTOs(teamAchievementService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamAchievementDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        TeamAchievement teamAchievement = teamAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(TeamAchievementMapper.INSTANCE.toDTO(teamAchievement));
    }
    @PostMapping
    public ResponseEntity<TeamAchievementDTO> create(@RequestBody TeamAchievement teamAchievement){
        return ResponseEntity.created(URI.create(PATH + "/" + teamAchievement.getId())).body(TeamAchievementMapper.INSTANCE.toDTO(teamAchievementService.save(teamAchievement)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeamAchievementDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody TeamAchievement teamAchievementDetails) throws ResourceNotFoundException{
        TeamAchievement teamAchievement = teamAchievementService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        teamAchievement.setDateAchieved(teamAchievementDetails.getDateAchieved());
        teamAchievement.setAward(teamAchievementDetails.getAward());
        teamAchievement.setTeam(teamAchievementDetails.getTeam());
        return ResponseEntity.created(URI.create(PATH + "/" + teamAchievement.getId())).body(TeamAchievementMapper.INSTANCE.toDTO(teamAchievementService.save(teamAchievement)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        TeamAchievement teamAchievement = teamAchievementService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        teamAchievementService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
