package com.letsplay.demo.product.DTOs;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateRequest(

    @NotBlank
    @Size(min = 3, max = 100)
    String name,

    @NotBlank
    @Size(min = 3, max = 1000)
    String description,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    Double price

) {}