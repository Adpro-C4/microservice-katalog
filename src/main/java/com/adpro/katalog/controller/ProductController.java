package com.adpro.katalog.controller;

import com.adpro.commonmodule.util.ResponseHandler;
import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    private static final String TOPICS = "/topic/product-update";
    private static final String update = "update";

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProductsAsJson() {
        List<Product> allProducts = service.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/{id}")
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
        messagingTemplate.convertAndSend(TOPICS,update);
        return ResponseEntity.ok(service.create(product));
    }


    @PostMapping("/edit") // edit using websocket
    public ResponseEntity<Object> editProductPost(@RequestBody Product product) {
        service.edit(product);
        messagingTemplate.convertAndSend(TOPICS,update);
        return ResponseHandler.generateResponse("ACC", HttpStatus.ACCEPTED, new HashMap<>());
    }

    @PostMapping("/delete") // delete using websocket
    public ResponseEntity<Object> deleteProductPost(@RequestBody Product product) {
        service.delete(product);
        messagingTemplate.convertAndSend(TOPICS,update);
        return ResponseHandler.generateResponse("ACC", HttpStatus.ACCEPTED, new HashMap<>());
    }
}