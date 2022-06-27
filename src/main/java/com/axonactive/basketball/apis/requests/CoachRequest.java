package com.axonactive.basketball.apis.requests;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CoachRequest {
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate dateStarted;
    private Double salaryExpected;
}
