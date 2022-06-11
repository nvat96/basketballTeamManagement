package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlayerRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate startedDate;
    private String typeOfPlayer;
    private Double salaryExpected;
}
