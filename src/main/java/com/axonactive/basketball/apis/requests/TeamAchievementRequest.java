package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class TeamAchievementRequest {
    private LocalDate dateAchieved;
    private String award;
    private String teamName;
}
