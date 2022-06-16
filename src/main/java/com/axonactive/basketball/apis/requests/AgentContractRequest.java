package com.axonactive.basketball.apis.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentContractRequest {
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String playerFirstName;
    private String playerLastName;
    private String agentFirstName;
    private String agentLastName;
}
