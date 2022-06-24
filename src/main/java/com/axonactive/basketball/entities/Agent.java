package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @Column(nullable = false)
    private String lastName;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String nationality;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    @Email
    private String email;
}
