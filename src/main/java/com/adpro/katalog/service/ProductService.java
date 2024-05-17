package com.adpro.katalog.service;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.model.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product findById(Long Id);
    public Product edit(Product product);
    public void delete(Product product);
    public void deleteAll();
    public void updateProductsStock(List<ProductDTO> productDTOs);
}