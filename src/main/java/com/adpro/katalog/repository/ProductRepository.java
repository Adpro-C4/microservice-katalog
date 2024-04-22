package com.adpro.katalog.repository;

import com.adpro.katalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private static int productCount = 1; // start id from 1

    public Product create(Product product) {
        product.setId(Integer.toString(productCount)); // make product id
        productCount++;
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (productId.equals(product.getId())) {
                return product;
            }
        }
        throw new NoSuchElementException("Product not found with ID: " + productId);
    }
}