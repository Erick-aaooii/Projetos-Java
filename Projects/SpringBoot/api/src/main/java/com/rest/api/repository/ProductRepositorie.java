package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.models.ProductModel;;

public interface ProductRepositorie extends JpaRepository<ProductModel, Integer> {
    
}
