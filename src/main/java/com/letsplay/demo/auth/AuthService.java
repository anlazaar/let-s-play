package com.letsplay.demo.auth;

import com.letsplay.demo.user.User;
import com.letsplay.demo.user.UserRepository;
import com.letsplay.demo.auth.DTOs.AuthResponse;
import com.letsplay.demo.auth.DTOs.LoginRequest;
import com.letsplay.demo.auth.DTOs.RegisterRequest;
import com.letsplay.demo.config.jwt.JwtService;
import com.letsplay.demo.exception.custom.ConflictException;
import com.letsplay.demo.exception.custom.UnauthorizedException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new ConflictException("Email already exists");
        }

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        user = userRepository.save(user);

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}