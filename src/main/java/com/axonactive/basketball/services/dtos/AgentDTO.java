package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private String name;
    private Gender gender;
    private Nationality nationality;
    private LocalDate dateOfBirth;
}
