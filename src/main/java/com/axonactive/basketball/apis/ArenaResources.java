package com.axonactive.basketball.apis;

import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.exceptions.ExceptionList;
import com.axonactive.basketball.services.impl.ArenaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(ArenaResources.PATH)
public class ArenaResources {
    @Autowired
    ArenaServiceImpl arenaService;
    public static final String PATH = "/api/arena";

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<Arena>> findAll() {
        return ResponseEntity.ok(arenaService.findAll());
    }

    @GetMapping("/findByName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<Arena> findByID(@RequestParam String arenaName) {
        try{
            return ResponseEntity.ok(arenaService.findByID(arenaName).get());
        }catch (Exception e){
            log.error("Find arena by name {} ", arenaName);
            throw ExceptionList.arenaNotFound();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Arena> create(@RequestBody Arena arena) {
        Arena createArena = arenaService.create(arena);
        if (createArena == null)
            throw ExceptionList.badRequest("NameAlreadyExist","Arena name already exist");
        else return ResponseEntity.ok(createArena);
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Arena> update(@RequestParam String arenaName, @RequestBody Arena arena) {
        try {
            return ResponseEntity.ok(arenaService.update(arenaName,arena));
        }catch (Exception e){
            log.error("Find arena by name {} ",arenaName);
            throw ExceptionList.arenaNotFound();
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<String> deleteByID(@RequestParam String arenaName) {
        try {
            arenaService.deleteByID(arenaName);
            return ResponseEntity.ok("Successfully deleted");
        }catch (Exception e){
            log.error("Find arena by name {} ",arenaName);
            throw ExceptionList.arenaNotFound();
        }
    }
}
