package com.axonactive.basketball.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwningCertificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateCreated;
    private Double sharePercent;
    @ManyToOne
    @JoinColumn
    private Team team;
    @ManyToOne
    @JoinColumn
    private Owner owner;
}
