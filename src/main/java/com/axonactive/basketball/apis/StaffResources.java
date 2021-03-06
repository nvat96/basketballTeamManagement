package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StaffRequest;
import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.services.dtos.StaffDTO;
import com.axonactive.basketball.services.impl.StaffServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(StaffResources.PATH)
public class StaffResources {
    public static final String PATH = "/api/staff";
    @Autowired
    StaffServiceImpl staffService;
    @Autowired
    TeamServiceImpl teamService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<StaffDTO>> findAll() {
        return ResponseEntity.ok(StaffMapper.INSTANCE.toDTOs(staffService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Staff> staff = staffService.findByID(id);
        if (staff.isPresent())
            return ResponseEntity.ok(staff);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }
    @GetMapping("/findByFirstNameOrLastName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findStaffByFirstNameOrLastName(@RequestParam(required = false, defaultValue = "") String firstName, @RequestParam(required = false, defaultValue = "") String lastName){
        List<Staff> staffs = staffService.findByFirstNameLikeAndLastNameLike(firstName, lastName);
        if (staffs.isEmpty())
            return ResponseEntity.ok("No staff match with first name " + firstName + " and last name " + lastName);
        else return ResponseEntity.ok(staffs);
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody StaffRequest staffRequest) {
        if (staffRequest.getTeamName() == null) {
            Staff staff = new Staff(null,
                    staffRequest.getFirstName(),
                    staffRequest.getLastName(),
                    staffRequest.getDateOfBirth(),
                    Gender.valueOf(staffRequest.getGender()),
                    staffRequest.getTitle(),
                    staffRequest.getSalary(),
                    null);
            return ResponseEntity.created(URI.create(PATH + "/" + staff.getId())).body(StaffMapper.INSTANCE.toDTO(staffService.save(staff)));
        } else {
            Optional<Team> team = teamService.findByID(staffRequest.getTeamName());
            if (!team.isPresent())
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + staffRequest.getTeamName());
            else {
                Staff staff = new Staff(null,
                        staffRequest.getFirstName(),
                        staffRequest.getLastName(),
                        staffRequest.getDateOfBirth(),
                        Gender.valueOf(staffRequest.getGender()),
                        staffRequest.getTitle(),
                        staffRequest.getSalary(),
                        team.get());
                return ResponseEntity.created(URI.create(PATH + "/" + staff.getId())).body(StaffMapper.INSTANCE.toDTO(staffService.save(staff)));
            }
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StaffRequest staffRequest) {
        Optional<Team> team = teamService.findByID(staffRequest.getTeamName());
        Optional<Staff> staff = staffService.findByID(id);
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + staffRequest.getTeamName());
        else if (staff.isPresent()) {
            staff.get().setFirstName(staffRequest.getFirstName());
            staff.get().setLastName(staffRequest.getLastName());
            staff.get().setGender(Gender.valueOf(staffRequest.getGender()));
            staff.get().setDateOfBirth(staffRequest.getDateOfBirth());
            staff.get().setTitle(staffRequest.getTitle());
            staff.get().setSalary(staffRequest.getSalary());
            staff.get().setTeam(team.get());
            return ResponseEntity.ok(StaffMapper.INSTANCE.toDTO(staffService.save(staff.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Staff> staff = staffService.findByID(id);
        if (staff.isPresent()) {
            staffService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }
}
