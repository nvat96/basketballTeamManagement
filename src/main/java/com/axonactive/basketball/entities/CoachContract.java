package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.TypeOfContract;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CoachContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateCreated;
    private LocalDate dateExpired;
    private String title;
    @Column(name = "salary")
    private Double salary;
    @Enumerated(value = EnumType.STRING)
    private TypeOfContract typeOfContract;
    private String body;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Coach coach;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Team team;

}
