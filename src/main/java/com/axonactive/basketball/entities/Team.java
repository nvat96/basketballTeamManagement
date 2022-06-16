package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.League;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {
    @Id
    private String name;
    private String location;
    private LocalDate dateFound;
    @Enumerated(value = EnumType.STRING)
    private League league;
    private Double salaryCap;
    @JoinColumn
    @ManyToOne
    private Arena arena;
}
