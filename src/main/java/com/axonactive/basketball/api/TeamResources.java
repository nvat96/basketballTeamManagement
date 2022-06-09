package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.TeamDTO;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(TeamResources.PATH)
public class TeamResources {
    public static final String PATH = "/api/team";
    @Autowired
    TeamServiceImpl teamService;
    @GetMapping
    public ResponseEntity<List<TeamDTO>> findAll(){
        return ResponseEntity.ok(TeamMapper.INSTANCE.toDTOs(teamService.findAll()));
    }
    @GetMapping("/{name}")
    public ResponseEntity<TeamDTO> findByID(@PathVariable(value = "name") String name) throws ResourceNotFoundException{
        Team team = teamService.findByID(name)
                .orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        return ResponseEntity.ok(TeamMapper.INSTANCE.toDTO(team));
    }
    @PostMapping
    public ResponseEntity<TeamDTO> create(@RequestBody Team team){
        return ResponseEntity.created(URI.create(PATH + "/" + team.getName())).body(TeamMapper.INSTANCE.toDTO(teamService.save(team)));
    }
    @PutMapping("/{name}")
    public ResponseEntity<TeamDTO> update(@PathVariable(value = "name") String name,
                                          @RequestBody Team teamDetails) throws ResourceNotFoundException{
        Team team = teamService.findByID(name).orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        team.setArena(teamDetails.getArena());
        team.setLocation(teamDetails.getLocation());
        team.setDateFound(teamDetails.getDateFound());
        team.setSalaryCap(teamDetails.getSalaryCap());
        team.setConference(teamDetails.getConference());
        return ResponseEntity.ok(TeamMapper.INSTANCE.toDTO(teamService.save(team)));
    }
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "name") String name) throws ResourceNotFoundException{
        Team team = teamService.findByID(name).orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        teamService.deleteByID(name);
        return ResponseEntity.noContent().build();
    }
}
