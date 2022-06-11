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
    private Double commissionRate;
    private String body;
    private String playerName;
    private String agentName;
}
