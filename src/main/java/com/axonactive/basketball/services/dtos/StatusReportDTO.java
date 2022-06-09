package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusReportDTO {
    private String playerName;
    private String status;
    private LocalDate dateCreated;
    private String comment;
}
