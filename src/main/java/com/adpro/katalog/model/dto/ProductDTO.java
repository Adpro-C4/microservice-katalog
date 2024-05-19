package com.adpro.katalog.model.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO implements Serializable{
    private String productId;
    private String name;
    private int quantity;
    private double price;
    public ProductDTO(
        // empty constructor for JPA
    ){

    }
    // constructor, getters, and setters
}