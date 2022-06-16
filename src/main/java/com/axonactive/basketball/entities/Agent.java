package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String nationality;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
}
