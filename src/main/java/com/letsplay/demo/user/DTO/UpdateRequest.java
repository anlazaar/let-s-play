package com.letsplay.demo.user.DTO;

import com.letsplay.demo.user.Role;

import jakarta.validation.constraints.*;

public record UpdateRequest(

    @NotBlank
    @Size(min = 3, max = 25)
    String name,

    @Email
    @NotBlank
    String email,

    @NotNull
    Role role

) {}