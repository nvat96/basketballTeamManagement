package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachContractDTO {
    private Integer id;
    private String typeOfContract;
    private String title;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String coachName;
    private String teamName;
}
