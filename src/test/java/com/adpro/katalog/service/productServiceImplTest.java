package com.adpro.katalog.service;

import com.adpro.katalog.controller.ProductWebSocketHandler;
import com.adpro.katalog.model.Product;
import com.adpro.katalog.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class productServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductWebSocketHandler productWebSocketHandler;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductWithZeroQuantity() {
        Product product = new Product.Builder()
                .id(1L)
                .name("Test Product")
                .quantity(0)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.create(product);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(productWebSocketHandler).sendMessageToAll(messageCaptor.capture());

        String expectedMessage = "Produk dengan id 1 kosong";
        assertEquals(expectedMessage, messageCaptor.getValue());
    }

    @Test
    void testEditProductToZeroQuantity() {
        Product product = new Product.Builder()
                .id(1L)
                .name("Test Product")
                .quantity(10)
                .build();

        Product updatedProduct = new Product.Builder()
                .id(1L)
                .quantity(0)
                .build();

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        productService.edit(updatedProduct);

        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);
        verify(productWebSocketHandler).sendMessageToAll(messageCaptor.capture());

        String expectedMessage = "Produk dengan id 1 kosong";
        assertEquals(expectedMessage, messageCaptor.getValue());
    }
}
