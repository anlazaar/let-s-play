package com.letsplay.demo.user.DTO;

import com.letsplay.demo.user.Role;

public record UserResponse(
    String id,
    String name,
    String email,
    Role role
) {
}