package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlayerRequest {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate startedDate;
    private String typeOfPlayer;
    private Double salaryExpected;
    private Double height;
    private Double weight;
}
