package com.axonactive.basketball.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
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
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;

    public void setTwoPointPercentage() {
        twoPointFGPercentage = (double) (totalTwoPointFG / twoPointFGMade);
    }

    public void setThreePointPercentage() {
        threePointFGPercentage = (double) (totalThreePointFG / threePointFGMade);
    }

    public void setFreeThrowPercentage() {
        freeThrowPercentage = (double) (totalFreeThrow/ freeThrowMade);
    }
}
