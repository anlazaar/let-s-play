package com.letsplay.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPatchRequest {
    private String name;

    @Email(message = "invalid updated email")
    private String email;

    @Size(min = 6, message = "updated password is less than 6 characters")
    private String password;

    @Pattern(regexp = "ADMIN|USER")
    private String role;
}
