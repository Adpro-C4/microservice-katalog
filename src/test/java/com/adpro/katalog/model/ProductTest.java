package com.adpro.katalog.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = new Product.Builder()
                .id(1L)
                .name("Sampo Kuda Nil")
                .description("Sampo yang sangat mantap")
                .price(12000)
                .discount(0)
                .brand("Kuda Nil")
                .category("Sampo")
                .image("https://cdn.dummyjson.com/product-images/1/1.jpg")
                .quantity(100)
                .build();
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
    void testGetProductDiscount() {
        assertEquals(0, this.product.getDiscount());
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

    @Test
    void testBuilder() {
        Product builtProduct = new Product.Builder()
                .id(2L)
                .name("Shampoo XYZ")
                .description("High-quality shampoo")
                .price(15000)
                .discount(10)
                .brand("XYZ")
                .category("Shampoo")
                .image("https://cdn.dummyjson.com/product-images/2/1.jpg")
                .quantity(50)
                .build();
        
        assertEquals(2L, builtProduct.getId());
        assertEquals("Shampoo XYZ", builtProduct.getName());
        assertEquals("High-quality shampoo", builtProduct.getDescription());
        assertEquals(15000, builtProduct.getPrice());
        assertEquals(10, builtProduct.getDiscount());
        assertEquals("XYZ", builtProduct.getBrand());
        assertEquals("Shampoo", builtProduct.getCategory());
        assertEquals("https://cdn.dummyjson.com/product-images/2/1.jpg", builtProduct.getImage());
        assertEquals(50, builtProduct.getQuantity());
    }

    @Test
    void testSetProductId() {
        product.setId(2L);
        assertEquals(2L, product.getId());
    }

    @Test
    void testSetProductName() {
        product.setName("New Shampoo");
        assertEquals("New Shampoo", product.getName());
    }

    @Test
    void testSetProductDescription() {
        product.setDescription("New Description");
        assertEquals("New Description", product.getDescription());
    }

    @Test
    void testSetProductPrice() {
        product.setPrice(13000);
        assertEquals(13000, product.getPrice());
    }

    @Test
    void testSetProductDiscount() {
        product.setDiscount(5);
        assertEquals(5, product.getDiscount());
    }

    @Test
    void testSetProductBrand() {
        product.setBrand("New Brand");
        assertEquals("New Brand", product.getBrand());
    }

    @Test
    void testSetProductCategory() {
        product.setCategory("New Category");
        assertEquals("New Category", product.getCategory());
    }

    @Test
    void testSetProductImage() {
        product.setImage("https://cdn.dummyjson.com/product-images/2/2.jpg");
        assertEquals("https://cdn.dummyjson.com/product-images/2/2.jpg", product.getImage());
    }

    @Test
    void testSetProductQuantity() {
        product.setQuantity(200);
        assertEquals(200, product.getQuantity());
    }
}
