package com.adpro.commonmodule.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = ThreadPoolConfig.class)
 class ThreadPoolConfigTest {

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
     void testThreadPoolTaskExecutorBean() {
        assertNotNull(threadPoolTaskExecutor);
        assertEquals(1800, threadPoolTaskExecutor.getCorePoolSize());
        assertEquals(2000, threadPoolTaskExecutor.getMaxPoolSize());
        assertEquals(200, threadPoolTaskExecutor.getQueueCapacity());
        assertEquals("ThreadPool-", threadPoolTaskExecutor.getThreadNamePrefix());
    }
}
