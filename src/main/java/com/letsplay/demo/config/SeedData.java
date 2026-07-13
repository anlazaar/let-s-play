package com.letsplay.demo.config;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.letsplay.demo.user.Role;
import com.letsplay.demo.user.User;
import com.letsplay.demo.user.UserRepository;

@Configuration
@RequiredArgsConstructor
public class SeedData {

    @Value("${admin.name}")
    private String adminName;

    @Value("${admin.email}")
    private String adminEmail;
    
    @Value("${admin.password}")
    private String adminPassword;

    @Bean
    CommandLineRunner initAdmin(
            UserRepository repo,
            BCryptPasswordEncoder encoder) {
        return args -> {
            if (repo.findByEmail(adminEmail).isEmpty()) {
                User admin = new User();
                admin.setName(adminName);
                admin.setEmail(adminEmail);
                admin.setPassword(encoder.encode(adminPassword));
                admin.setRole(Role.ADMIN);
                
                repo.save(admin);
            }
        };
    }
}