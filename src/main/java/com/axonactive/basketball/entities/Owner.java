package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Enumerated(value = EnumType.STRING)
    private Nationality nationality;
    private LocalDate dateOwned;
    @Column(name = "share")
    private Double sharePercent;
    @JoinColumn
    @ManyToOne
    private Team team;
}
