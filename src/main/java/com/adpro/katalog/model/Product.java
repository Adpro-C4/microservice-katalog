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

    // Default constructor for JPA
    public Product() {}

    // use Builder build pattern
    private Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.discount = builder.discount;
        this.brand = builder.brand;
        this.category = builder.category;
        this.image = builder.image;
        this.quantity = builder.quantity;
    }

    public static class Builder {
        private Long id;
        private String name = "";
        private String description = "";
        private int price = 0;
        private int discount = 0;
        private String brand = "";
        private String category = "";
        private String image = "";
        private int quantity = 0;

        public Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(int price) {
            this.price = price;
            return this;
        }

        public Builder discount(int discount) {
            this.discount = discount;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
