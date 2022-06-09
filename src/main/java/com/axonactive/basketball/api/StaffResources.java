package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Staff;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.StaffDTO;
import com.axonactive.basketball.services.impl.StaffServiceImpl;
import com.axonactive.basketball.services.mappers.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StaffResources.PATH)
public class StaffResources {
    public static final String PATH = "/api/staff";
    @Autowired
    StaffServiceImpl staffService;
    @GetMapping
    public ResponseEntity<List<StaffDTO>> findAll(){
        return ResponseEntity.ok(StaffMapper.INSTANCE.toDTOs(staffService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Staff staff = staffService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(StaffMapper.INSTANCE.toDTO(staff));
    }
    @PostMapping
    public ResponseEntity<StaffDTO> create(@RequestBody Staff staff){
        return ResponseEntity.created(URI.create(PATH + "/" + staff.getId())).body(StaffMapper.INSTANCE.toDTO(staffService.save(staff)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StaffDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Staff staffDetails) throws ResourceNotFoundException{
        Staff staff = staffService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        staff.setName(staffDetails.getName());
        staff.setGender(staffDetails.getGender());
        staff.setDateOfBirth(staffDetails.getDateOfBirth());
        staff.setTitle(staffDetails.getTitle());
        staff.setSalary(staffDetails.getSalary());
        staff.setTeam(staffDetails.getTeam());
        return ResponseEntity.created(URI.create(PATH + "/" + staff.getId())).body(StaffMapper.INSTANCE.toDTO(staffService.save(staff)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Staff staff = staffService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        staffService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
