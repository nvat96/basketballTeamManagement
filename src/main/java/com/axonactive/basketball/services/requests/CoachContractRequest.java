package com.axonactive.basketball.services.requests;

import com.axonactive.basketball.enums.TypeOfContract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoachContractRequest {
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String title;
    private Double salary;
    private TypeOfContract typeOfContract;
    private String body;
    private String coachName;
    private String teamName;
}
