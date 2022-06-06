package com.axonactive.basketball.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArenaDTO {
    private String name;
    private String location;
    private Integer capacity;
    private LocalDate dateBuilt;
}
