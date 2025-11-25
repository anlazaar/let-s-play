package com.letsplay.shop.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductPatchRequest {
    private String name;

    @Min(value = 0, message = "Price cannot be negative")
    private Double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;
}
