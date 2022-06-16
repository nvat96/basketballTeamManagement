package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.OwningCertificateRequest;
import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.entities.OwningCertificate;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.services.dtos.OwningCertificateDTO;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.impl.OwningCertificateServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.OwningCertificateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(OwningCertificateResources.PATH)
public class OwningCertificateResources {
    public static final String PATH = "/api/owningCertificate";
    @Autowired
    OwningCertificateServiceImpl owningCertificateService;
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    OwnerServiceImpl ownerService;

    @GetMapping
    private ResponseEntity<List<OwningCertificateDTO>> findAll() {
        return ResponseEntity.ok(OwningCertificateMapper.INSTANCE.toDTOs(owningCertificateService.findAll()));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<OwningCertificate> owningCertificate = owningCertificateService.findByID(id);
        if (owningCertificate.isPresent()) {
            return ResponseEntity.ok(owningCertificate);
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owning certificate ID not found: " + id);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody OwningCertificateRequest owningCertificateRequest) {
        Optional<Team> team = teamService.findByID(owningCertificateRequest.getTeamName());
        Optional<Owner> owner = ownerService.findByFirstNameAndLastNameLike(owningCertificateRequest.getOwnerFirstName(), owningCertificateRequest.getOwnerLastName());
        if (!team.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + owningCertificateRequest.getTeamName());
        } else if (!owner.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner name not found: " + owningCertificateRequest.getOwnerFirstName() + " " + owningCertificateRequest.getOwnerLastName());
        } else {
            OwningCertificate owningCertificate = new OwningCertificate(null,
                    owningCertificateRequest.getDateCreated(),
                    owningCertificateRequest.getSharePercent(),
                    team.get(),
                    owner.get());
            return ResponseEntity.created(URI.create(PATH + "/" + owningCertificate.getId())).body(OwningCertificateMapper.INSTANCE.toDTO(owningCertificateService.save(owningCertificate)));
        }
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                     @RequestBody OwningCertificateRequest owningCertificateRequest) {
        Optional<OwningCertificate> owningCertificate = owningCertificateService.findByID(id);
        Optional<Team> team = teamService.findByID(owningCertificateRequest.getTeamName());
        Optional<Owner> owner = ownerService.findByFirstNameAndLastNameLike(owningCertificateRequest.getOwnerFirstName(), owningCertificateRequest.getOwnerLastName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + owningCertificateRequest.getTeamName());
        else if (!owner.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner name not found: " + owningCertificateRequest.getOwnerFirstName() + " " + owningCertificateRequest.getOwnerLastName());
        else if (!owningCertificate.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owning certificate ID not found: " + id);
        else {
            owningCertificate.get().setDateCreated(owningCertificateRequest.getDateCreated());
            owningCertificate.get().setSharePercent(owningCertificateRequest.getSharePercent());
            owningCertificate.get().setTeam(team.get());
            owningCertificate.get().setOwner(owner.get());
            return ResponseEntity.ok(OwningCertificateMapper.INSTANCE.toDTO(owningCertificateService.save(owningCertificate.get())));
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<OwningCertificate> owningCertificate = owningCertificateService.findByID(id);
        if (owningCertificate.isPresent()) {
            owningCertificateService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owning certificate ID not found: " + id);
    }
}
