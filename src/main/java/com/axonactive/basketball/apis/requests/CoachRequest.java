package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CoachRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate dateStarted;
    private Double salaryExpected;
}
