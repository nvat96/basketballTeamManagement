package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAchievementDTO {
    private Integer id;
    private String award;
    private LocalDate dateAchieved;
    private String playerName;
}
