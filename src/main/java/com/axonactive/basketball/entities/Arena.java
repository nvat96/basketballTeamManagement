package com.axonactive.basketball.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Arena {
    @Id
    private String name;
    private String location;
    private Integer capacity;
    private LocalDate dateBuilt;
}
