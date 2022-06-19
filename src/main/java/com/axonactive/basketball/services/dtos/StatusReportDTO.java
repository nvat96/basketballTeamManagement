package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusReportDTO {
    private Integer id;
    private String playerName;
    private LocalDate dateInjured;
    private LocalDate dateRecovered;
    private String comment;
    private String status;
}
