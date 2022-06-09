package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private LocalDate startedDate;
    private String typeOfPlayer;
}
