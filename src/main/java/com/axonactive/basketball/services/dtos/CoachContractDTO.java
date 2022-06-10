package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachContractDTO {
    private Integer id;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String title;
    private Double salary;
    private String typeOfContract;
    private String coachName;
    private String teamName;
}
