package com.letsplay.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 6, message = "Password must be at least 6 charcaters")
    private String password;
}
