package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {
    private Integer id;
    private String name;
    private String teamName;
    private Double sharePercent;
}
