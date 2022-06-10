package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.enums.Nationality;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachDTO {
    private Integer id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate dateStarted;
}
