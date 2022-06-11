package com.axonactive.basketball.services.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachAchievementDTO {
    private Integer id;
    private String award;
    private LocalDate dateAchieved;
    private String coachName;
}
