package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StatusReportRequest {
    private String status;
    private LocalDate dateCreated;
    private String comment;
    private String playerName;
}
