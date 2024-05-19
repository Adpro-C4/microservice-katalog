package com.adpro.commonmodule.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = RestConfig.class) // Specify the configuration class
public class RestConfigTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private RestConfig restConfig;

    @Test
    public void testRestTemplateBean() {
        assertNotNull(restConfig.restTemplate());
    }
}