package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Conference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Team {
    @Id
    private String name;
    private String location;
    private LocalDate dateFound;
    private Double salaryCap;
    @Enumerated(value = EnumType.STRING)
    private Conference conference;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Arena arena;
}
