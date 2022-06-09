package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Owner;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.OwnerDTO;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.mappers.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(OwnerResources.PATH)
public class OwnerResources {
    public static final String PATH = "/api/owner";
    @Autowired
    OwnerServiceImpl ownerService;
    @GetMapping
    public ResponseEntity<List<OwnerDTO>> findAll(){
        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTOs(ownerService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Owner owner = ownerService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(OwnerMapper.INSTANCE.toDTO(owner));
    }
    @PostMapping
    public ResponseEntity<OwnerDTO> create(@RequestBody Owner owner){
        return ResponseEntity.created(URI.create(PATH + "/" + owner.getId())).body(OwnerMapper.INSTANCE.toDTO(ownerService.save(owner)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Owner ownerDetails) throws ResourceNotFoundException{
        Owner owner = ownerService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        owner.setName(ownerDetails.getName());
        owner.setNationality(ownerDetails.getNationality());
        owner.setGender(ownerDetails.getGender());
        owner.setDateOfBirth(ownerDetails.getDateOfBirth());
        owner.setDateOwned(ownerDetails.getDateOwned());
        owner.setSharePercent(ownerDetails.getSharePercent());
        owner.setTeam(ownerDetails.getTeam());
        return ResponseEntity.created(URI.create(PATH + "/" + owner.getId())).body(OwnerMapper.INSTANCE.toDTO(ownerService.save(owner)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Owner owner = ownerService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        ownerService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}

