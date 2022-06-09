package com.axonactive.basketball.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String name;
    private LocalDate dateOfBirth;
    private String position;
    private String typeOfPlayer;
    private Double height;
    private String team;
    private String status;
}
