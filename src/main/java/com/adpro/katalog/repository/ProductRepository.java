package com.adpro.katalog.repository;

import com.adpro.katalog.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}