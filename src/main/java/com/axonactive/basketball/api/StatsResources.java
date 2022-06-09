package com.axonactive.basketball.api;

import com.axonactive.basketball.entities.Player;
import com.axonactive.basketball.entities.Stats;
import com.axonactive.basketball.exceptions.ResourceNotFoundException;
import com.axonactive.basketball.services.dtos.StatsDTO;
import com.axonactive.basketball.services.impl.StatsServiceImpl;
import com.axonactive.basketball.services.mappers.StatsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(StatsResources.PATH)
public class StatsResources {
    public static final String PATH = "/api/stats";
    @Autowired
    StatsServiceImpl statsService;
    @GetMapping
    public ResponseEntity<List<StatsDTO>> findAll(){
        return ResponseEntity.ok(StatsMapper.INSTANCE.toDTOs(statsService.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<StatsDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Stats stats = statsService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
        return ResponseEntity.ok(StatsMapper.INSTANCE.toDTO(stats));
    }
    @PostMapping
    public ResponseEntity<StatsDTO> create(@RequestBody Stats stats){
        return ResponseEntity.created(URI.create(PATH + "/" + stats.getId())).body(StatsMapper.INSTANCE.toDTO(statsService.save(stats)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<StatsDTO> update(@PathVariable(value = "id") Integer id,
                                           @RequestBody Stats statsDetails) throws ResourceNotFoundException{
        Stats stats = statsService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
         stats.setHeight(statsDetails.getHeight());
         stats.setWeight(statsDetails.getWeight());
         stats.setTotalTwoPointFG(statsDetails.getTotalTwoPointFG());
         stats.setTwoPointFGMade(statsDetails.getTwoPointFGMade());
         stats.setTotalThreePointFG(statsDetails.getTotalThreePointFG());
         stats.setThreePointFGMade(statsDetails.getThreePointFGMade());
         stats.setTotalFreeThrow(statsDetails.getTotalFreeThrow());
         stats.setFreeThrowMade(statsDetails.getFreeThrowMade());
         stats.setSteal(statsDetails.getSteal());
         stats.setBlock(statsDetails.getBlock());
         stats.setRebound(statsDetails.getRebound());
         stats.setFoul(statsDetails.getFoul());
         stats.setTurnover(statsDetails.getTurnover());
        return ResponseEntity.created(URI.create(PATH + "/" + stats.getId())).body(StatsMapper.INSTANCE.toDTO(statsService.save(stats)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
        Stats stats = statsService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
        statsService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
