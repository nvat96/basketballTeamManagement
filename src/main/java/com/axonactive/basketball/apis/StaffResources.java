package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.StaffRequest;
import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.services.dtos.StaffDTO;
import com.axonactive.basketball.services.impl.StaffServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.StaffMapper;
import com.sun.tools.javac.jvm.Gen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<StaffDTO>> findAll() {
        return ResponseEntity.ok(StaffMapper.INSTANCE.toDTOs(staffService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<Staff> staff = staffService.findByID(id);
        if (staff.isPresent())
            return ResponseEntity.ok(StaffMapper.INSTANCE.toDTO(staff.get()));
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StaffRequest staffRequest) {
        Optional<Team> team = teamService.findByID(staffRequest.getTeamName());
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + staffRequest.getTeamName());
        else {
            Staff staff = new Staff(null,
                    staffRequest.getName(),
                    staffRequest.getDateOfBirth(),
                    Gender.valueOf(staffRequest.getGender()),
                    staffRequest.getTitle(),
                    staffRequest.getSalary(),
                    team.get());
            return ResponseEntity.created(URI.create(PATH + "/" + staff.getId())).body(StaffMapper.INSTANCE.toDTO(staffService.save(staff)));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody StaffRequest staffRequest) {
        Optional<Team> team = teamService.findByID(staffRequest.getTeamName());
        Optional<Staff> staff = staffService.findByID(id);
        if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + staffRequest.getTeamName());
        else if (staff.isPresent()) {
            staff.get().setName(staffRequest.getName());
            staff.get().setGender(Gender.valueOf(staffRequest.getGender()));
            staff.get().setDateOfBirth(staffRequest.getDateOfBirth());
            staff.get().setTitle(staffRequest.getTitle());
            staff.get().setSalary(staffRequest.getSalary());
            staff.get().setTeam(team.get());
            return ResponseEntity.ok(StaffMapper.INSTANCE.toDTO(staffService.save(staff.get())));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<Staff> staff = staffService.findByID(id);
        if (staff.isPresent()) {
            staffService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Staff ID not found: " + id);
    }
}
