package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ArenaRequest {
    private String location;
    private Integer capacity;
    private LocalDate dateBuilt;
}
