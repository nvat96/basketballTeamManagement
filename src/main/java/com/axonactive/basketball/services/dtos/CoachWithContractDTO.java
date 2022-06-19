package com.axonactive.basketball.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoachWithContractDTO {
    private Integer contractId;
    private Integer coachId;
    private String coachName;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private Double salary;
    private String teamName;
}
