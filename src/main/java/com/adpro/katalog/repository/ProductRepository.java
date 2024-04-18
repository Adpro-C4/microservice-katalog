package com.adpro.katalog.repository;

import com.adpro.katalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public  Iterator<Product> findAll() {
        return  productData.iterator();
    }
}