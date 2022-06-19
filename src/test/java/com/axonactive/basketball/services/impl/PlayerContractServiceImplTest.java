package com.axonactive.basketball.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PlayerContractServiceImplTest {
    @Autowired
    PlayerContractServiceImpl playerContractService;
    @Test
    void findByPlayerId() {
        assertEquals(3,playerContractService.findByPlayerId(4).size());
    }

    @Test
    void findByTeamName() {
        assertEquals(5,playerContractService.findByTeamNameLike("Los Angeles").size());
    }

    @Test
    void findPlayerWithContractThatExpiredInThatYear() {
        assertEquals(8,playerContractService.findPlayerWithContractThatExpiredInThatYear(2020).size());
    }

    @Test
    void findPlayerContractThatAreActiveOfATeam() {
        assertEquals(8,playerContractService.findPlayerContractThatAreActiveOfATeam("Los Angeles Lakers").size());
    }
}