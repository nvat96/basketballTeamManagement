package com.axonactive.basketball.services.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StatusReportServiceImplTest {
    @Autowired
    StatusReportServiceImpl statusReportService;
    @Test
    void findByTeamNameAndYear() {
        assertEquals(5,statusReportService.findByTeamNameAndYear(" ",2021).size());
    }
}