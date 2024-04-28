package com.adpro.katalog.service;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public Product findById(Long productId) throws NoSuchElementException {
        // Use getReferenceById method from JpaRepository to find a product by ID
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            return product;
        } else {
            throw new NoSuchElementException("No such product with id: " + productId);
        }
    }

    @Override
    public Product edit(Product updatedProduct) throws NoSuchElementException {
        Long productId = updatedProduct.getId();
        Product product = findById(productId);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setDiscount(updatedProduct.getDiscount());
        product.setBrand(updatedProduct.getBrand());
        product.setCategory(updatedProduct.getCategory());
        product.setImage(updatedProduct.getImage());
        product.setQuantity(updatedProduct.getQuantity());;

        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) throws NoSuchElementException {
        productRepository.delete(product);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();

    }
}