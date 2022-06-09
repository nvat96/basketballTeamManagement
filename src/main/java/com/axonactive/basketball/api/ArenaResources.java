package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.ArenaDTO;
import com.axonactive.basketball.services.impl.ArenaServiceImpl;
import com.axonactive.basketball.services.mappers.ArenaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ArenaResources.PATH)
public class ArenaResources {
    @Autowired
    ArenaServiceImpl arenaService;
    public static final String PATH = "/api/arena";

    @GetMapping
    public ResponseEntity<List<ArenaDTO>> findAll(){
        return ResponseEntity.ok(ArenaMapper.INSTANCE.toDTOs(arenaService.findAll()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<ArenaDTO> findByID(@PathVariable(value = "name") String name) throws ResourceNotFoundException{
        Arena arena = arenaService.findByID(name).orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        return ResponseEntity.ok(ArenaMapper.INSTANCE.toDTO(arena));
    }

    @PostMapping
    public ResponseEntity<ArenaDTO> create(@RequestBody Arena arena){
        arenaService.save(arena);
        return ResponseEntity.created(URI.create(PATH + "/" + arena.getName())).body(ArenaMapper.INSTANCE.toDTO(arena));
    }

    @PutMapping("/{name}")
    public ResponseEntity<ArenaDTO> update(@PathVariable(value = "name") String name,@RequestBody Arena arenaDetails) throws ResourceNotFoundException{
        Arena arena = arenaService.findByID(name).orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        arena.setCapacity(arenaDetails.getCapacity());
        arena.setLocation(arenaDetails.getLocation());
        arena.setDateBuilt(arenaDetails.getDateBuilt());
        return ResponseEntity.ok(ArenaMapper.INSTANCE.toDTO(arenaService.save(arena)));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "name") String name) throws ResourceNotFoundException{
        Arena arena = arenaService.findByID(name).orElseThrow(() -> new ResourceNotFoundException("Name not found: " + name));
        arenaService.deleteByID(name);
        return ResponseEntity.noContent().build();
    }
}
