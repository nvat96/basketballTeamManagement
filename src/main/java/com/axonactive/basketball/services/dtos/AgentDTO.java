package com.axonactive.basketball.services.dtos;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private Integer id;
    private String agentName;
    private String email;
}
