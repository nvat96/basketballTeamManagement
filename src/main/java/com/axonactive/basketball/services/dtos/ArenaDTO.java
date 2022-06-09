package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArenaDTO {
    private String name;
    private String location;
    private Integer capacity;
    private LocalDate dateBuilt;
}
