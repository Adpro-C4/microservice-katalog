package com.adpro.katalog.repository;

import com.adpro.katalog.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    JpaRepository<Product, Long> jpaRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Test
    void testDeleteAll() {
        productRepository.deleteAll();
        List<Product> productList = productRepository.findAll();
        assertTrue(productList.isEmpty());
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setId(Long.MAX_VALUE);
        product.setName("Sampo Cap Bambang");
        product.setQuantity(100);

        Product savedProduct = productRepository.save(product);
        assertNotNull(savedProduct);
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());

        List<Product> productList = productRepository.findAll();
        assertFalse(productList.isEmpty());
        int lastIndex = productList.size() - 1;
        assertEquals(product.getId(), productList.get(lastIndex).getId());
        assertEquals(product.getName(), productList.get(lastIndex).getName());
        assertEquals(product.getQuantity(), productList.get(lastIndex).getQuantity());
    }


    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setId(Long.MAX_VALUE);
        product1.setName("Sampo Cap Usep");
        product1.setQuantity(50);
        productRepository.save
        (product1);

        Product product2 = new Product();
        product2.setId(Long.MAX_VALUE);
        product2.setName("Sampo Cap Bambang");
        product2.setQuantity(100);
        productRepository.save
        (product2);

        List<Product> productList = productRepository.findAll();
        int lastIndex = productList.size() - 1;
        assertFalse(productList.isEmpty());
        assertEquals(product1.getId(), productList.get(lastIndex-1).getId());
        assertEquals(product2.getId(), productList.get(lastIndex).getId());
    }

    @Test
    void testFindById() {
        Product product1 = new Product();
        product1.setId(Long.MAX_VALUE);
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(100);
        productRepository.save(product1);

        Optional<Product> searchedProduct = productRepository.findById(Long.MAX_VALUE);
        Product foundProduct = searchedProduct.get();
        assertEquals(product1.getId(), foundProduct.getId());

    }

}
