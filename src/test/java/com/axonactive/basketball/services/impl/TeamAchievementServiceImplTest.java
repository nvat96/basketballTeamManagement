package com.axonactive.basketball.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeamAchievementServiceImplTest {
    @Autowired
    TeamAchievementServiceImpl teamAchievementService;
    @Test
    void findByTeamNameLike() {
        assertEquals(3,teamAchievementService.findByTeamNameLike("Los").size());
    }
}