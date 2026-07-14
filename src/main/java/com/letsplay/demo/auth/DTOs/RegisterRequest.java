package com.letsplay.demo.auth.DTOs;

import jakarta.validation.constraints.*;

public record RegisterRequest(

        @NotBlank @Size(min = 3, max = 25) String name,

        @Email @NotBlank String email,

        @NotBlank @Size(min = 8, max = 30) String password

) {
}