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
    private Double gamePlayed;
    private Double points;
    private Double assists;
    private Double steals;
    private Double blocks;
    private Double rebounds;
    private Double threePointerMade;
    private Double fieldGoalPercentage;
    private Double freeThrowPercentage;
    private Integer season;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;
}
