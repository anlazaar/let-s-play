package com.letsplay.shop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.letsplay.shop.models.User;
import com.letsplay.shop.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRespository;
    private final PasswordEncoder passwdEncoder;

    public User createUser(User user) {
        Optional<User> existing = userRespository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email already taken");
}

        user.setPassword(passwdEncoder.encode(user.getPassword()));

        return userRespository.save(user);
    }

    public List<User> getAllUsers() {
        return userRespository.findAll();
    }

    public User getUserById(String id) {
        return userRespository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String id, User usernewinfo) {
        User existing = getUserById(id);

        existing.setName(usernewinfo.getName());
        existing.setEmail(usernewinfo.getEmail());
        existing.setRole(usernewinfo.getRole());
        existing.setPassword(passwdEncoder.encode(usernewinfo.getPassword()));

        return userRespository.save(existing);
    }

    public void deleteUser(String id) {
        userRespository.deleteById(id);
    }
}
