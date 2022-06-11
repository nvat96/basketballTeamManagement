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
@ToString
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private String title;
    @Column(name = "salary($m/year)")
    private Double salary;
    @JoinColumn
    @ManyToOne
    private Team team;
}
