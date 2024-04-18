package com.adpro.katalog.controller;

import com.adpro.katalog.model.Product;
import com.adpro.katalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class HomePageController {

    @Autowired
    private ProductService service;

    @GetMapping("")
    public String HomePage(){
        return "HomePage";
    }
}