package com.letsplay.demo.user.DTO;

import com.letsplay.demo.user.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record EditRequest(

        @Size(min = 3, max = 25) String name,

        @Email String email,

        Role role,

        @Size(min = 8, max = 30) String password

) {
}