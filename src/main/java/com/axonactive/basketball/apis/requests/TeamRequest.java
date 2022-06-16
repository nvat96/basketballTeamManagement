package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamRequest {
    private String name;
    private String location;
    private LocalDate dateFound;
    private String league;
    private Double salaryCap;
    private String arenaName;
}
