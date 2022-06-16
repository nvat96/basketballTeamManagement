package com.axonactive.basketball.apis.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StatsRequest {
    private Double height;
    private Double weight;
    private Integer totalTwoPointFG;
    private Integer twoPointFGMade;
    private Double twoPointFGPercentage;
    private Integer totalThreePointFG;
    private Integer threePointFGMade;
    private Double threePointFGPercentage;
    private Integer totalFreeThrow;
    private Integer freeThrowMade;
    private Double freeThrowPercentage;
    private Integer steal;
    private Integer block;
    private Integer rebound;
    private Integer foul;
    private Integer turnover;
    private String playerFirstName;
    private String playerLastName;

    public void setTwoPointPercentage() {
        twoPointFGPercentage = (double) (totalTwoPointFG / twoPointFGMade);
    }

    public void setThreePointPercentage() {
        threePointFGPercentage = (double) (totalThreePointFG / threePointFGMade);
    }

    public void setFreeThrowPercentage() {
        freeThrowPercentage = (double) (totalFreeThrow / freeThrowMade);
    }
}
