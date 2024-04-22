package com.adpro.katalog.model;
import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private int price;
    private int discount;
    private String brand;
    private String category;
    private String image;
    private int quantity;
}