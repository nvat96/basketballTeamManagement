package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Position;
import com.axonactive.basketball.enums.TypeOfContract;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlayerContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String body;
    private Integer number;
    @Column(name = "salary($m/year)")
    private Double salary;
    @Enumerated(value = EnumType.STRING)
    private TypeOfContract typeOfContract;
    @Enumerated(value = EnumType.STRING)
    private Position position;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Team team;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;


}
