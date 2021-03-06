package com.axonactive.basketball.apis.requests;

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
    private String typeOfContract;
    private String body;
    private String coachFirstName;
    private String coachLastName;
    private String teamName;
}
