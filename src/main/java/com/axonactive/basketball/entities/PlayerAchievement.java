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
public class PlayerAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private Award award;
    private LocalDate dateAchieved;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;
}
