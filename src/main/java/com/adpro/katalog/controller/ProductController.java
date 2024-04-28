package com.adpro.katalog.controller;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    public @ResponseBody List<Product> getAllProductsAsJson() {
        List<Product> allProducts = service.findAll();
        return allProducts;
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

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") Long productId, Model model) {
        Product productToEdit = service.findById(productId);
        model.addAttribute("product", productToEdit);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.edit(product);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Product productToDelete, Model model) {
        service.delete(productToDelete);
        return "redirect:../list";
    }

    @GetMapping("/deleteAll") /// PAS DELETE ALL GA RESET ID NYA
    public String deleteAllProducts(Model model) {
        service.deleteAll();
        return "redirect:list";
    }
}