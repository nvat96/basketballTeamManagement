package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Award;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TeamAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateAchieved;
    @Enumerated(value = EnumType.STRING)
    private Award award;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Team team;
}
