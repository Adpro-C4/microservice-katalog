package com.adpro.katalog.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class APITest {

    @Test
    public void testGetOrderDetailsUrl() {
        String expectedUrl = "https://api-gateway-specialitystore.up.railway.app/purchase-service/order/view/order-data/";
        String actualUrl = API.GET_ORDER_DETAILS.getUrl();
        assertEquals(expectedUrl, actualUrl);
    }
}
