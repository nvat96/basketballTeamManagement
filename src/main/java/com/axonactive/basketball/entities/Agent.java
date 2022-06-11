package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Nationality nationality;
    private LocalDate dateOfBirth;
    @Column(name = "commissionRateExpected(%)")
    private Double commissionRateExpected;
    private String phoneNumber;
}
