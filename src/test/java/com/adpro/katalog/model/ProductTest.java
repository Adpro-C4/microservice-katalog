package com.adpro.katalog.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ProductTest {
    
    Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product();
        this.product.setId(1L);
        this.product.setName("Sampo Kuda Nil");
        this.product.setDescription("Sampo yang sangat mantap");
        this.product.setPrice(12000);
        this.product.setDiscount(0);
        this.product.setBrand("Kuda Nil");
        this.product.setCategory("Sampo");
        this.product.setImage("https://cdn.dummyjson.com/product-images/1/1.jpg");
        this.product.setQuantity(100);
    }

    @Test
    void testGetProductId() {
        assertEquals(1L, this.product.getId());
    }

    @Test
    void testGetProductName() {
        assertEquals("Sampo Kuda Nil", this.product.getName());
    }

    @Test
    void testGetProductDescription() {
        assertEquals("Sampo yang sangat mantap", this.product.getDescription());
    }

    @Test
    void testGetProductPrice() {
        assertEquals(12000, this.product.getPrice());
    }

    @Test
    void testGetProductBrand() {
        assertEquals("Kuda Nil", this.product.getBrand());
    }
    
    @Test
    void testGetProductCategory() {
        assertEquals("Sampo", this.product.getCategory());
    }

    @Test
    void testGetProductImage() {
        assertEquals("https://cdn.dummyjson.com/product-images/1/1.jpg", this.product.getImage());
    }

    @Test
    void testGetProductQuantity() {
        assertEquals(100, this.product.getQuantity());
    }
}