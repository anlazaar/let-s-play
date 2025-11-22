package com.letsplay.shop.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.letsplay.shop.dto.LoginRequest;
import com.letsplay.shop.dto.RegisterRequest;
import com.letsplay.shop.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> postMethodName(@RequestBody RegisterRequest request) {
        String token = authService.register(request.getName(), request.getEmail(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
