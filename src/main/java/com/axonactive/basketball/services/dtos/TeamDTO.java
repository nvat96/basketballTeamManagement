package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private String name;
    private String location;
    private String league;
    private String arenaName;
    private LocalDate dateFound;
}
