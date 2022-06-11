package com.axonactive.basketball.apis.requests;

import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class StaffRequest {
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String title;
    private Double salary;
    private String teamName;
}
