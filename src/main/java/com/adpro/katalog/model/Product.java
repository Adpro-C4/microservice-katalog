package com.adpro.katalog.model;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class Product {
    private String id;
    private String name = "";
    private String description = "";
    private int price = 0;
    private int discount = 0;
    private String brand = "";
    private String category = "";
    private String image = "";
    private int quantity = 0;
}