package com.letsplay.demo.product.DTOs;

public record ProductResponse(
        String id,
        String name,
        String description,
        double price,
        String userId
) {
}
