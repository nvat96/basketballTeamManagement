package com.axonactive.basketball.services.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwningCertificateDTO {
    private Integer id;
    private String teamName;
    private String ownerName;
}
