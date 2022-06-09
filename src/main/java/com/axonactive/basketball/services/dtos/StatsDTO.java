package com.axonactive.basketball.services.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO {
    private String playerName;
    private Double height;
    private Double weight;
    private Double twoPointPercentage;
    private Double threePointPercentage;
    private Double freeThrowPercentage;
}
