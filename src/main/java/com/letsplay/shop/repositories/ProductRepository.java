package com.letsplay.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsplay.shop.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
