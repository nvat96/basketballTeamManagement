package com.axonactive.basketball.apis;

import com.axonactive.basketball.apis.requests.CoachContractRequest;
import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.entities.CoachContract;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.TypeOfContract;
import com.axonactive.basketball.services.dtos.CoachContractDTO;
import com.axonactive.basketball.services.impl.CoachContractServiceImpl;
import com.axonactive.basketball.services.impl.CoachServiceImpl;
import com.axonactive.basketball.services.impl.OwnerServiceImpl;
import com.axonactive.basketball.services.impl.TeamServiceImpl;
import com.axonactive.basketball.services.mappers.CoachContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CoachContractResources.PATH)
public class CoachContractResources {
    public static final String PATH = "/api/coachContract";
    @Autowired
    CoachContractServiceImpl coachContractService;
    @Autowired
    CoachServiceImpl coachService;
    @Autowired
    TeamServiceImpl teamService;
    @Autowired
    OwnerServiceImpl ownerService;

    @GetMapping
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<List<CoachContractDTO>> findAll() {
        return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTOs(coachContractService.findAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByID(@PathVariable(value = "id") Integer id) {
        Optional<CoachContract> coachContract = coachContractService.findByID(id);
        if (coachContract.isPresent())
            return ResponseEntity.ok(coachContract);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach contract ID not found: " + id);
    }

    @GetMapping("/findContractActive")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findContractActive(@RequestParam(defaultValue = "") String teamName) {
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.ok("No team name match with " + teamName);
        else return ResponseEntity.ok(coachContractService.findCoachContractThatAreActiveOfATeam(teamName));
    }
    @GetMapping("/findByCoachID")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByCoachID(@RequestParam(defaultValue = "0") Integer coachID){
        Optional<Coach> coach = coachService.findByID(coachID);
        if (!coach.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach ID not found: " + coachID);
        else return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTOs(coachContractService.findByCoachId(coachID)));
    }
    @GetMapping("/findByTeamName")
    @PreAuthorize("hasAnyRole('MANAGEMENT', 'INVESTOR')")
    public ResponseEntity<?> findByTeamName(@RequestParam(defaultValue = "") String teamName){
        List<Team> teams = teamService.findByNameLike(teamName);
        if (teams.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No team name match with " + teamName);
        else return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTOs(coachContractService.findByTeamNameLike(teamName)));
    }

    @PostMapping
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> create(@RequestBody CoachContractRequest coachContractRequest) {
        Optional<Coach> coach = coachService.findByFirstNameAndLastName(coachContractRequest.getCoachFirstName(), coachContractRequest.getCoachLastName());
        Optional<Team> team = teamService.findByID(coachContractRequest.getTeamName());
        if (!coach.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach name not found: " + coachContractRequest.getCoachFirstName() + " " + coachContractRequest.getCoachLastName());
        else if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + coachContractRequest.getTeamName());
        else {
            Double salaryMustPay = ownerService.calculateSalaryMustPay(coachContractRequest.getTeamName()) + coachContractRequest.getSalary();
            if (teamService.isSalaryMustPayOverSalaryCap(coachContractRequest.getTeamName(), salaryMustPay))
                return ResponseEntity.ok("The salary of the contract you about to create is above the salary cap of the team " + coachContractRequest.getTeamName() +
                        "\nTeam " + coachContractRequest.getTeamName() + " salary must pay: $" + salaryMustPay + "m/year" +
                        "\nTeam " + coachContractRequest.getTeamName() + "'s salary cap: $" + team.get().getSalaryCap() + "m/year");
            else {
                CoachContract coachContract = new CoachContract(null,
                        coachContractRequest.getDateCreated(),
                        coachContractRequest.getDateExpired(),
                        coachContractRequest.getTitle(),
                        coachContractRequest.getSalary(),
                        TypeOfContract.valueOf(coachContractRequest.getTypeOfContract()),
                        coachContractRequest.getBody(),
                        coach.get(),
                        team.get());
                return ResponseEntity.created(URI.create(PATH + "/" + coachContract.getId())).body(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract)));
            }
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody CoachContractRequest coachContractRequest) {
        Optional<Coach> coach = coachService.findByFirstNameAndLastName(coachContractRequest.getCoachFirstName(), coachContractRequest.getCoachLastName());
        Optional<Team> team = teamService.findByID(coachContractRequest.getTeamName());
        Optional<CoachContract> coachContract = coachContractService.findByID(id);
        if (!coach.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach name not found: " + coachContractRequest.getCoachFirstName() + " " + coachContractRequest.getCoachLastName());
        else if (!team.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Team name not found: " + coachContractRequest.getTeamName());
        else if (coachContract.isPresent()) {
            Double salaryMustPay = ownerService.calculateSalaryMustPay(coachContractRequest.getTeamName()) + coachContractRequest.getSalary() - coachContract.get().getSalary();
            if (teamService.isSalaryMustPayOverSalaryCap(coachContractRequest.getTeamName(), salaryMustPay))
                return ResponseEntity.ok("The salary of the contract you about to update is above the salary cap of the team " + coachContractRequest.getTeamName() +
                        "\nTeam " + coachContractRequest.getTeamName() + " salary must pay: $" + salaryMustPay + "m/year" +
                        "\nTeam " + coachContractRequest.getTeamName() + "'s salary cap: $" + team.get().getSalaryCap() + "m/year");
            else {
                coachContract.get().setDateCreated(coachContractRequest.getDateCreated());
                coachContract.get().setDateExpired(coachContractRequest.getDateExpired());
                coachContract.get().setTitle(coachContractRequest.getTitle());
                coachContract.get().setSalary(coachContractRequest.getSalary());
                coachContract.get().setTypeOfContract(TypeOfContract.valueOf(coachContractRequest.getTypeOfContract()));
                coachContract.get().setBody(coachContractRequest.getBody());
                coachContract.get().setCoach(coach.get());
                coachContract.get().setTeam(team.get());
                return ResponseEntity.ok(CoachContractMapper.INSTANCE.toDTO(coachContractService.save(coachContract.get())));
            }
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach contract ID not found: " + id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGEMENT')")
    public ResponseEntity<?> deleteByID(@PathVariable(value = "id") Integer id) {
        Optional<CoachContract> coachContract = coachContractService.findByID(id);
        if (coachContract.isPresent()) {
            coachContractService.deleteByID(id);
            return ResponseEntity.ok("Successfully deleted");
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coach contract ID not found: " + id);
    }
}
