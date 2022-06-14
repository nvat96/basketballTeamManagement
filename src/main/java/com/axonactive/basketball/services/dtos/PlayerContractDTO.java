package com.axonactive.basketball.services.dtos;

import com.axonactive.basketball.enums.Position;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerContractDTO {
    private Integer id;
    private String typeOfContract;
    private String position;
    private String teamName;
    private String playerName;
}
