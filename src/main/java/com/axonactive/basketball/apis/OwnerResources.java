package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.OwnerRequest;
import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import com.axonactive.basketball.services.dtos.OwnerDTO;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(OwnerResources.PATH)
public class OwnerResources {
    public static final String PATH = "/api/owner";
    @Autowired
    OwnerServiceImpl ownerService;
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll() {
        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTOs(ownerService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Owner> owner = ownerService.findByID(id);
        if (owner.isPresent())
            return ResponseEntity.ok(owner);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OwnerRequest ownerRequest) {
        if (ownerRequest.getTeamName() == null) {
            Owner owner = new Owner(null,
                    ownerRequest.getName(),
                    ownerRequest.getDateOfBirth(),
                    Gender.valueOf(ownerRequest.getGender()),
                    Nationality.valueOf(ownerRequest.getNationality()),
                    ownerRequest.getDateOwned(),
                    ownerRequest.getSharePercent(),
                    null);
            return ResponseEntity.created(URI.create(PATH + "/" + owner.getId())).body(OwnerMapper.INSTANCE.toDTO(ownerService.save(owner)));
        } else {
            Optional<Team> team = teamService.findByID(ownerRequest.getTeamName());
            if (!team.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + ownerRequest.getTeamName());
            else {
                Owner owner = new Owner(null,
                        ownerRequest.getName(),
                        ownerRequest.getDateOfBirth(),
                        Gender.valueOf(ownerRequest.getGender()),
                        Nationality.valueOf(ownerRequest.getNationality()),
                        ownerRequest.getDateOwned(),
                        ownerRequest.getSharePercent(),
                        team.get());
                return ResponseEntity.created(URI.create(PATH + "/" + owner.getId())).body(OwnerMapper.INSTANCE.toDTO(ownerService.save(owner)));
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody OwnerRequest ownerRequest) {
        Optional<Team> team = teamService.findByID(ownerRequest.getTeamName());
        Optional<Owner> owner = ownerService.findByID(id);
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + ownerRequest.getTeamName());
        else if (!owner.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
        else {
            owner.get().setName(ownerRequest.getName());
            owner.get().setDateOfBirth(ownerRequest.getDateOfBirth());
            owner.get().setGender(Gender.valueOf(ownerRequest.getGender()));
            owner.get().setNationality(Nationality.valueOf(ownerRequest.getNationality()));
            owner.get().setDateOwned(ownerRequest.getDateOwned());
            owner.get().setSharePercent(ownerRequest.getSharePercent());
            owner.get().setTeam(team.get());
            return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTO(owner.get()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Owner> owner = ownerService.findByID(id);
        if (owner.isPresent()) {
            ownerService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
    }
}

