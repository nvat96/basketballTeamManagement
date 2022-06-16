package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlayerContractRequest {
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String body;
    private Integer number;
    private Double salary;
    private String typeOfContract;
    private String position;
    private String teamName;
    private String playerFirstName;
    private String playerLastName;
}
