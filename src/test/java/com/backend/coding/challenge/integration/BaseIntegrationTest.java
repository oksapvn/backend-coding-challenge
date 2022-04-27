package com.backend.coding.challenge.integration;

import com.backend.coding.challenge.integration.config.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc
public class BaseIntegrationTest {

    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mvc;

    @BeforeEach
    public void setUpBase() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
}
