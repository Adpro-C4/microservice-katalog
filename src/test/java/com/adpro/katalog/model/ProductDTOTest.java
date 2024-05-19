package com.adpro.katalog.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import com.adpro.katalog.model.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 class ProductDTOTest {

    private ProductDTO productDTO;

    @BeforeEach
     void setUp() {
        productDTO = new ProductDTO();
        productDTO.setProductId("123");
        productDTO.setName("Test Product");
        productDTO.setQuantity(10);
        productDTO.setPrice(99.99);
    }

    @Test
     void testProductDTOGettersAndSetters() {
        assertEquals("123", productDTO.getProductId());
        assertEquals("Test Product", productDTO.getName());
        assertEquals(10, productDTO.getQuantity());
        assertEquals(99.99, productDTO.getPrice());
    }

    @Test
     void testProductDTOConstructor() {
        ProductDTO newProductDTO = new ProductDTO();
        newProductDTO.setProductId("456");
        newProductDTO.setName("New Product");
        newProductDTO.setQuantity(20);
        newProductDTO.setPrice(199.99);

        assertEquals("456", newProductDTO.getProductId());
        assertEquals("New Product", newProductDTO.getName());
        assertEquals(20, newProductDTO.getQuantity());
        assertEquals(199.99, newProductDTO.getPrice());
    }

    @Test
     void testProductDTOJsonSerialization() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(productDTO);
        ProductDTO deserializedProductDTO = objectMapper.readValue(jsonString, ProductDTO.class);

        assertEquals(productDTO.getProductId(), deserializedProductDTO.getProductId());
        assertEquals(productDTO.getName(), deserializedProductDTO.getName());
        assertEquals(productDTO.getQuantity(), deserializedProductDTO.getQuantity());
        assertEquals(productDTO.getPrice(), deserializedProductDTO.getPrice());
    }

    @Test
     void testProductDTOJsonDeserialization() throws IOException {
        String jsonString = "{\"productId\":\"789\",\"name\":\"Serialized Product\",\"quantity\":30,\"price\":299.99}";
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO deserializedProductDTO = objectMapper.readValue(jsonString, ProductDTO.class);

        assertEquals("789", deserializedProductDTO.getProductId());
        assertEquals("Serialized Product", deserializedProductDTO.getName());
        assertEquals(30, deserializedProductDTO.getQuantity());
        assertEquals(299.99, deserializedProductDTO.getPrice());
    }
}
