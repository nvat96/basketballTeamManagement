package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamAchievementDTO {
    private Integer id;
    private String award;
    private LocalDate dateAchieved;
    private String teamName;
}
