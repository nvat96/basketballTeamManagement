package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwningCertificateRequest {
    private LocalDate dateCreated;
    private Double sharePercent;
    private String teamName;
    private String ownerFirstName;
    private String ownerLastName;
}
