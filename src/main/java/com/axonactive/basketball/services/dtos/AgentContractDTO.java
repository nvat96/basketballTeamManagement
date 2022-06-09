package com.axonactive.basketball.services.dtos;

import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentContractDTO {
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private Double commissionRate;
    private String playerName;
    private String agentName;
}
