package com.adpro.katalog.service;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements  ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        // Use save method from JpaRepository to create or update the product
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        // Use findAll method from JpaRepository to fetch all products
        return productRepository.findAll();
    }

    public Product findById(Long productId) throws NoSuchElementException {
        // Use getById method from JpaRepository to find a product by ID
        return productRepository.getById(productId);
    }
}