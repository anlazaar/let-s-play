package com.letsplay.demo.user;

import org.mapstruct.Mapper;

import com.letsplay.demo.user.DTO.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
