package com.adpro.katalog.eventdriven;

import com.adpro.katalog.model.dto.ProductDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RabbitMQListenerTest {

    @Test
    void jsonToProductDTOList() throws IOException {
        // Arrange
        String jsonString = "{\"cartItems\":[{\"id\":1,\"quantity\":2},{\"id\":2,\"quantity\":3}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode cartItemsNode = objectMapper.readTree(jsonString).get("cartItems");
        RabbitMQListener rabbitMQListener = new RabbitMQListener(null); // Pass null for ProductService

        // Act
        List<ProductDTO> productDTOs = rabbitMQListener.jsonToProductDTOList(cartItemsNode);

        // Assert
        assertEquals(2, productDTOs.size());
        assertEquals(2, productDTOs.get(0).getQuantity());
        assertEquals(3, productDTOs.get(1).getQuantity());
    }
}