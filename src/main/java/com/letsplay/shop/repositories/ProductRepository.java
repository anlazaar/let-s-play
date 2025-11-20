package com.letsplay.shop.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.letsplay.shop.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
