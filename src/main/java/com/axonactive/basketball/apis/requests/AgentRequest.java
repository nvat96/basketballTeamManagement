package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentRequest {
    private String firstName;
    private String lastName;
    private String gender;
    private String nationality;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
}
