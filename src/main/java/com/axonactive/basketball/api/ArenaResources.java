package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.services.impl.ArenaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ArenaResources.PATH)
public class ArenaResources {
    @Autowired
    ArenaServiceImpl arenaService;
    public static final String PATH = "/api/arena";

    @GetMapping
    public ResponseEntity<List<Arena>> findAll() {
        return ResponseEntity.ok(arenaService.findAll());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> findByID(@PathVariable(value = "name") String name) {
        Optional<Arena> arena = arenaService.findByID(name);
        if (arena.isPresent())
            return ResponseEntity.ok(arena);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name not found: " + name);
    }

    @PostMapping
    public ResponseEntity<Arena> create(@RequestBody Arena arena) {
        return ResponseEntity.created(URI.create(PATH + "/" + arena.getName())).body(arenaService.save(arena));
    }

    @PutMapping("/{name}")
    public ResponseEntity<?> update(@PathVariable(value = "name") String name, @RequestBody Arena arenaDetails) {
        Optional<Arena> arena = arenaService.findByID(name);
        if (arena.isPresent()) {
            arena.get().setCapacity(arenaDetails.getCapacity());
            arena.get().setLocation(arenaDetails.getLocation());
            arena.get().setDateBuilt(arenaDetails.getDateBuilt());
            return ResponseEntity.ok(arenaService.save(arena.get()));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name not found: " + name);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "name") String name) {
        Optional<Arena> arena = arenaService.findByID(name);
        if (arena.isPresent()) {
            arenaService.deleteByID(name);
            return ResponseEntity.ok("Successfully deleted");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name not found: " + name);
    }
}
