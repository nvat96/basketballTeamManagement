package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.ArenaRequest;
import com.axonactive.basketball.entities.Arena;
import com.axonactive.basketball.exceptions.ExceptionList;
import com.axonactive.basketball.services.impl.ArenaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{name}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<Arena> findByID(@RequestParam String name) {
        log.error("Find arena by name {} ", name);
        return ResponseEntity.ok(arenaService.findByID(name));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Arena> create(@RequestBody @Valid Arena arena) {
        return ResponseEntity.ok(arenaService.create(arena));
    }

    @PutMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<Arena> update(@RequestParam String name, @RequestBody ArenaRequest arenaRequest) {
        return ResponseEntity.ok(arenaService.update(name, arenaRequest));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<String> deleteByID(@RequestParam String name) {
        Arena arena = arenaService.findByID(name);
        if (arena != null) {
            arenaService.deleteByID(name);
            return ResponseEntity.ok("Successfully deleted");
        } else throw ExceptionList.arenaNotFound();
    }
}
