package com.axonactive.basketball.services.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO {
    private Integer id;
    private String playerName;
    private Double height;
    private Double twoPointFGPercentage;
    private Double threePointFGPercentage;
    private Double freeThrowPercentage;
}
