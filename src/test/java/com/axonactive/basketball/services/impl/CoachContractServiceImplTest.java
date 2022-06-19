package com.axonactive.basketball.services.impl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoachContractServiceImplTest {
    @Autowired
    CoachContractServiceImpl coachContractService;
    @Test
    void findCoachContractThatAreActiveOfATeam() {
        assertEquals(0,coachContractService.findCoachContractThatAreActiveOfATeam("Los Angeles").size());
    }
}