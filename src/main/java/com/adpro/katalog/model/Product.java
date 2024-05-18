package com.adpro.katalog.model;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;




@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name = "";
    private String description = "";
    private int price = 0;
    private int discount = 0;
    private String brand = "";
    private String category = "";
    private String image = "";
    private int quantity = 0;
}
