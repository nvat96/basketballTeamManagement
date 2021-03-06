package com.axonactive.basketball.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AgentContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Agent agent;
}
