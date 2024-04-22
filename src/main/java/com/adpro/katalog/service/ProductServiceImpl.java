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
        productRepository.create(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findById(String productId) throws NoSuchElementException {
        Iterator<Product> iterator = productRepository.findAll();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        throw new NoSuchElementException("No such product with id: " + productId);
    }
}