package com.adpro.katalog.eventdriven;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

 class RabbitMQConfigTest {

    @Test
     void testQueuesExist() {
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

        Queue purchaseQueue = rabbitMQConfig.purchaseQueue();
        assertNotNull(purchaseQueue);

        Queue orderQueue = rabbitMQConfig.orderQueue();
        assertNotNull(orderQueue);

        Queue trackingOrderQueue = rabbitMQConfig.trackingOrderQueue();
        assertNotNull(trackingOrderQueue);

        Queue stokProdukQueue = rabbitMQConfig.stokProdukQueue();
        assertNotNull(stokProdukQueue);
    }

    @Test
     void testExchangeExists() {
        RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

        Exchange exchange = rabbitMQConfig.exchange();
        assertNotNull(exchange);
        assertTrue(exchange instanceof DirectExchange);
    }
}
