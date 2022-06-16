package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAchievementRequest {
    private String award;
    private LocalDate dateAchieved;
    private String playerFirstName;
    private String playerLastName;
}
