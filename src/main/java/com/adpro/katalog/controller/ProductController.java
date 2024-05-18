package com.adpro.katalog.controller;

import com.adpro.commonmodule.util.ResponseHandler;
import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<List<Product>> getAllProductsAsJson() {
        List<Product> allProducts = service.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> getProductById(@PathVariable("id") Long productId) {
        try {
            Product product = service.findById(productId);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            String errorMessage = "No such product with id: " + productId;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProductPost(@RequestBody Product product) {
        service.create(product);
        return ResponseEntity.ok(service.create(product));
    }


    @PostMapping("/edit") // edit using websocket
    public ResponseEntity<Object> editProductPost(@RequestBody Product product) {
        service.edit(product);
        return ResponseHandler.generateResponse("ACC", HttpStatus.ACCEPTED, new HashMap<>());
    }

    @PostMapping("/delete") // delete using websocket
    public ResponseEntity<Object> deleteProductPost(@RequestBody Product product) {
        service.delete(product);
        return ResponseHandler.generateResponse("ACC", HttpStatus.ACCEPTED, new HashMap<>());
    }
}