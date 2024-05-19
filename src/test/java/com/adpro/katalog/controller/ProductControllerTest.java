package com.adpro.katalog.controller;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private SimpMessagingTemplate messagingTemplate;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product(); // Initialize product
        product.setId(1L);
        product.setName("Test Product");
    }

    @Test
    void getAllProductsAsJson() throws Exception {
        when(productService.findAll()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"));
    }

    @Test
    void getProductById() throws Exception {
        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void getProductById_NotFound() throws Exception {
        when(productService.findById(1L)).thenThrow(new RuntimeException("No such product with id: 1"));

        mockMvc.perform(MockMvcRequestBuilders.get("/product/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("No such product with id: 1"));
    }

    @Test
    void createProductPost() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Test Product\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));

        verify(messagingTemplate, times(1)).convertAndSend("/topic/product-update", "update");
    }

    @Test
    void editProductPost() throws Exception {
        when(productService.edit(any(Product.class))).thenReturn(product);;

        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Updated Product\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("ACC"));

        verify(messagingTemplate, times(1)).convertAndSend("/topic/product-update", "update");
    }

    @Test
    void deleteProductPost() throws Exception {
        doNothing().when(productService).delete(any(Product.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/product/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Test Product\"}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("ACC"));

        verify(messagingTemplate, times(1)).convertAndSend("/topic/product-update", "update");
    }
}
