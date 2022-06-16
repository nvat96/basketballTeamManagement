package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String nationality;
    private LocalDate dateStarted;
    private Double salaryExpected;

}
