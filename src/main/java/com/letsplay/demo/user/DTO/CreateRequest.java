package com.letsplay.demo.user.DTO;

import com.letsplay.demo.user.Role;

import jakarta.validation.constraints.*;

public record CreateRequest(

    @NotBlank
    @Size(min = 3, max = 25)
    String name,

    @Email
    @NotBlank
    String email,

    @NotNull
    Role role,

    @NotBlank
    @Size(min = 8, max = 30)
    String password

) {}