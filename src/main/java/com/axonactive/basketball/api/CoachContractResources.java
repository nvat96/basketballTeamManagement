//package com.axonactive.basketball.api;
//
//import com.axonactive.basketball.entities.CoachContract;
//import com.axonactive.basketball.exceptions.ResourceNotFoundException;
//import com.axonactive.basketball.services.dtos.CoachContractDTO;
//import com.axonactive.basketball.services.impl.CoachContractServiceImpl;
//import com.axonactive.basketball.services.impl.CoachServiceImpl;
//import com.axonactive.basketball.services.impl.TeamServiceImpl;
//import com.axonactive.basketball.services.mappers.CoachContractMapper;
//import com.axonactive.basketball.services.requests.CoachContractRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping(CoachContractResources.PATH)
//public class CoachContractResources {
//    public static final String PATH = "/api/coachContract";
//    @Autowired
//    CoachContractServiceImpl coachContractService;
//    @Autowired
//    CoachServiceImpl coachService;
//    @Autowired
//    TeamServiceImpl teamService;
//    @GetMapping
//    public ResponseEntity<List<CoachContractDTO>> findAll(){
//        return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTOs(coachContractService.findAll()));
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<CoachContractDTO> findByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
//        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
//        return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTO(coachContract));
//    }
//    @PostMapping
//    public ResponseEntity<CoachContractDTO> create(@RequestBody CoachContractRequest coachContractRequest,
//                                                   @RequestParam String coachName,
//                                                   @RequestParam String teamName){
//        CoachContract coachContract = new CoachContract(null,
//                coachContractRequest.getDateCreated(),
//                coachContractRequest.getDateExpired(),
//                coachContractRequest.getTitle(),
//                coachContractRequest.getSalary(),
//                coachContractRequest.getTypeOfContract(),
//                coachContractRequest.getBody(),
//                coachService.findByName(coachName),
//                teamService.findByName(teamName));
//        return ResponseEntity.created(URI.create(PATH + "/" + coachContract.getId())).body(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract)));
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<CoachContractDTO> update(@PathVariable(value = "id") Integer id,
//                                                    @RequestBody CoachContractRequest coachContractRequest) throws ResourceNotFoundException{
//        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
//        coachContract.setDateCreated(coachContractRequest.getDateCreated());
//        coachContract.setDateExpired(coachContractRequest.getDateExpired());
//        coachContract.setTitle(coachContractRequest.getTitle());
//        coachContract.setSalary(coachContractRequest.getSalary());
//        coachContract.setTypeOfContract(coachContractRequest.getTypeOfContract());
//        coachContract.setBody(coachContractRequest.getBody());
//        coachContract.setCoach(coachService.findByName(coachContractRequest.getCoachName()));
//        coachContract.setTeam(teamService.findByName(coachContractRequest.getTeamName()));
//        return ResponseEntity.created(URI.create(PATH + "/" + coachContract.getId())).body(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract)));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteByID(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException{
//        CoachContract coachContract = coachContractService.findByID(id).orElseThrow(()->new ResourceNotFoundException("ID not found: " + id));
//        coachContractService.deleteByID(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}
