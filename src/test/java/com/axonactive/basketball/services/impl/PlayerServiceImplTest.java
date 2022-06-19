package com.axonactive.basketball.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PlayerServiceImplTest {
    @Autowired
    PlayerServiceImpl playerService;
    @Test
    void findPlayerThatPlayForThatTeamAtThatYear() {
        assertEquals(5,playerService.findPlayerThatPlayForThatTeamAtThatYear(2011,"Los Angeles Lakers").size());
    }

    @Test
    void findByFirstNameLikeAndLastNameLike() {
        assertEquals(2,playerService.findByFirstNameLikeAndLastNameLike("Kev%","%").size());
    }
}