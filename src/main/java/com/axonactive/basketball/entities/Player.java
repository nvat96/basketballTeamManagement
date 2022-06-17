package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.TypeOfPlayer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String nationality;
    private LocalDate startedDate;
    @Enumerated(EnumType.STRING)
    private TypeOfPlayer typeOfPlayer;
    private Double salaryExpected;
    private Double height;
    private Double weight;
}
