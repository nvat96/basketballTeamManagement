package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.OwnerRequest;
import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.services.dtos.OwnerDTO;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<OwnerDTO>> findAll() {
        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTOs(ownerService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Owner> owner = ownerService.findByID(id);
        if (owner.isPresent())
            return ResponseEntity.ok(owner);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findOwnerByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        List<Owner> owners = ownerService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (owners.isEmpty())
            return ResponseEntity.ok("No owner match with first name " + firstName + " and last name " + lastName);
        else return ResponseEntity.ok(owners);
    }
    @GetMapping("/totalSalaryMustPay")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> calculateTotalSalaryMustPayForATeam(@RequestParam(defaultValue = "0")String teamName){
        Optional<Team> team = teamService.findByID(teamName);
        if (!team.isPresent())
            return ResponseEntity.ok("Team name not found " + teamName);
        else return ResponseEntity.ok("Total salary must pay for team " + teamName + " is $" +ownerService.calculateSalaryMustPay(teamName) + "m/year");
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody OwnerRequest ownerRequest) {
        Owner owner = new Owner(null,
                ownerRequest.getFirstName(),
                ownerRequest.getLastName(),
                ownerRequest.getDateOfBirth(),
                Gender.valueOf(ownerRequest.getGender()),
                ownerRequest.getNationality());
        return ResponseEntity.created(URI.create(PATH + "/" + owner.getId())).body(OwnerMapper.INSTANCE.toDTO(ownerService.save(owner)));
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody OwnerRequest ownerRequest) {
        Optional<Owner> owner = ownerService.findByID(id);
        if (!owner.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
        else {
            owner.get().setFirstName(ownerRequest.getFirstName());
            owner.get().setLastName(ownerRequest.getLastName());
            owner.get().setDateOfBirth(ownerRequest.getDateOfBirth());
            owner.get().setGender(Gender.valueOf(ownerRequest.getGender()));
            owner.get().setNationality(ownerRequest.getNationality());
            return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTO(owner.get()));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Owner> owner = ownerService.findByID(id);
        if (owner.isPresent()) {
            ownerService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Owner ID not found: " + id);
    }
}

