package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.enums.Gender;
import com.axonactive.basketball.enums.Nationality;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {
    private Integer id;
    private String name;
    private String gender;
    private String nationality;
    private LocalDate dateOfBirth;
}
