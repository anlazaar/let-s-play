package com.letsplay.shop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsplay.shop.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
