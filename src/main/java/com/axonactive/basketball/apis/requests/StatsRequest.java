package com.axonactive.basketball.apis.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StatsRequest {
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
    private String playerFirstName;
    private String playerLastName;

}
