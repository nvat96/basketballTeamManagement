package com.axonactive.basketball.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Stats {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private Double height;
    private Double weight;
    private Integer totalTwoPointFG;
    private Integer twoPointFGMade;
    private Double twoPointPercentage;
    private Integer totalThreePointFG;
    private Integer threePointFGMade;
    private Double threePointPercentage;
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
        twoPointPercentage = Double.valueOf(twoPointFGMade/totalTwoPointFG);
    }

    public void setThreePointPercentage() {
        threePointPercentage = Double.valueOf(threePointFGMade/totalThreePointFG);
    }

    public void setFreeThrowPercentage() {
        freeThrowPercentage = Double.valueOf(freeThrowMade/totalFreeThrow);
    }
}
