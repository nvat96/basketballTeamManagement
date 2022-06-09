package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.entities.Coach;
import com.axonactive.basketball.entities.Team;
import com.axonactive.basketball.enums.TypeOfContract;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachContractDTO {
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String title;
    private Double salary;
    private String typeOfContract;
    private String coachName;
    private String teamName;
}
