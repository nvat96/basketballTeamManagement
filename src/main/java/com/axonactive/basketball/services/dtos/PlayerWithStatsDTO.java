package com.axonactive.basketball.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerWithStatsDTO {
    private String fullName;
    private Double gamePlayed;
    private Double points;
    private Double assists;
    private Double steals;
    private Double blocks;
    private Double rebounds;
    private Double threePointerPercentage;
    private Double fieldGoalPercentage;
    private Double freeThrowPercentage;
    private Integer season;
}
