package com.axonactive.basketball.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class AgentServiceImplTest {

    @Autowired
    AgentServiceImpl agentService;
    @Test
    void findAll() {
        log.error("Error message");
        log.warn("Warn message");
        log.info("Info message");
        log.trace("Trace message");
        log.debug("Debug message");
    }

    @Test
    void deleteByID() {
        log.error("Error message");
        log.info("Info message");
    }
}