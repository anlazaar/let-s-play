package com.letsplay.shop.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.letsplay.shop.models.User;
import com.letsplay.shop.repositories.UserRepository;
import com.letsplay.shop.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwdEnco;
    private final JwtService jwtService;

    public String login(String email, String passwd) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("No such a user with this email"));

        if (!passwdEnco.matches(passwd, user.getPassword())) {
            throw new RuntimeException("Password Incorrect");
        }

        return jwtService.generateToken(user);
    }

    public String register(String name, String email, String password) {

        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already taken");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwdEnco.encode(password));
        user.setRole("USER"); // this will be the default we need to figure out how to change it!

        userRepo.save(user);

        return jwtService.generateToken(user);
    }
}
