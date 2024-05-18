package com.adpro.katalog.service;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.model.dto.ProductDTO;
import com.adpro.katalog.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100);
        product.setDiscount(10);
        product.setBrand("Test Brand");
        product.setCategory("Test Category");
        product.setImage("test_image.png");
        product.setQuantity(50);
    }

    @Test
    void testCreate() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertNotNull(createdProduct);
        assertEquals(product.getId(), createdProduct.getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testFindAll() {
        List<Product> products = Collections.singletonList(product);
        when(productRepository.findAll()).thenReturn(products);
        List<Product> foundProducts = productService.findAll();
        assertNotNull(foundProducts);
        assertEquals(1, foundProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        Product foundProduct = productService.findById(product.getId());
        assertNotNull(foundProduct);
        assertEquals(product.getId(), foundProduct.getId());
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testFindByIdNotFound() {
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> productService.findById(product.getId()));
        verify(productRepository, times(1)).findById(product.getId());
    }

    @Test
    void testEdit() {
        Product updatedProduct = new Product();
        updatedProduct.setId(product.getId());
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated Description");
        updatedProduct.setPrice(200);
        updatedProduct.setDiscount(20);
        updatedProduct.setBrand("Updated Brand");
        updatedProduct.setCategory("Updated Category");
        updatedProduct.setImage("updated_image.png");
        updatedProduct.setQuantity(100);

        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product editedProduct = productService.edit(updatedProduct);
        assertNotNull(editedProduct);
        assertEquals(updatedProduct.getName(), editedProduct.getName());
        assertEquals(updatedProduct.getDescription(), editedProduct.getDescription());
        verify(productRepository, times(1)).findById(product.getId());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testEditNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setId(99L);
        when(productRepository.findById(updatedProduct.getId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> productService.edit(updatedProduct));
        verify(productRepository, times(1)).findById(updatedProduct.getId());
    }

    @Test
    void testDelete() {
        doNothing().when(productRepository).delete(product);
        productService.delete(product);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testDeleteAll() {
        doNothing().when(productRepository).deleteAll();
        productService.deleteAll();
        verify(productRepository, times(1)).deleteAll();
    }

    @Test
    void testUpdateProductsStock() {
        List<ProductDTO> productDTOs = new ArrayList<>();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId("1");
        productDTO.setQuantity(5);
        productDTOs.add(productDTO);

        when(productRepository.findAllById(anySet())).thenReturn(Collections.singletonList(product));

        productService.updateProductsStock(productDTOs);

        assertEquals(45, product.getQuantity()); // Original quantity 50 - 5 from productDTO
        verify(productRepository, times(1)).findAllById(anySet());
        verify(productRepository, times(1)).saveAll(anyList());
    }
}
