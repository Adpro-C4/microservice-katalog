package com.adpro.katalog.controller;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testGetAllProductsAsJson() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productService.findAll()).thenReturn(products);

        ResponseEntity<List<Product>> response = productController.getAllProductsAsJson();

        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetProductById() {
        Product product = new Product();
        when(productService.findById(1L)).thenReturn(product);

        ResponseEntity<Object> response = productController.getProductById(1L);

        assertEquals(product, response.getBody());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testGetProductByIdNotFound() {
        Product product = new Product();
        when(productService.findById(5L)).thenReturn(product);

        ResponseEntity<Object> response = productController.getProductById(1L);

        assertEquals("No such product with id: 1", response.getBody());
        verify(productService, times(1)).findById(1L);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(any(Product.class))).thenReturn(product);

        ResponseEntity<Product> response = productController.createProductPost(product);

        assertEquals(product, response.getBody());
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        ResponseEntity<Object> response = productController.editProductPost(product);

        HashMap<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "ACC");
        expectedResponse.put("status", 202);
        expectedResponse.put("data", new HashMap<>());

        assertEquals(expectedResponse, response.getBody());
        verify(productService, times(1)).edit(product);
    }

    @Test
    void testDeleteProductPost() {
        Product product = new Product();
        ResponseEntity<Object> response = productController.deleteProductPost(product);

        HashMap<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("message", "ACC");
        expectedResponse.put("status", 202);
        expectedResponse.put("data", new HashMap<>());

        assertEquals(expectedResponse, response.getBody());
        verify(productService, times(1)).delete(product);
    }
}
