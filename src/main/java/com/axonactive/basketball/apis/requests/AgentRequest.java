package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentRequest {
    private String name;
    private String gender;
    private String nationality;
    private LocalDate dateOfBirth;
    private Double commissionRateExpected;
    private String phoneNumber;
    private String email;
}
