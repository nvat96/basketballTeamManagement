package com.axonactive.basketball.entities;

import com.axonactive.basketball.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private Status status;
    private LocalDate dateInjured;
    private LocalDate dateRecovered;
    private String comment;
    @JoinColumn
    @ManyToOne
    @NotNull
    private Player player;
}
