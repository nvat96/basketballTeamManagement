package com.axonactive.basketball.apis.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoachAchievementRequest {
    private String award;
    private LocalDate dateAchieved;
    private String coachFirstName;
    private String coachLastName;
}
