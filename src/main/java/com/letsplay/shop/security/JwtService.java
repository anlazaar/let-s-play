package com.letsplay.shop.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.letsplay.shop.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    protected final String secret = "HysvyuV234_YUV234uvst2342vsdfiweipbwejkfjmasbHJBy@DFVN#F#23672923HJ2367@2";
    private final Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    private final long EXPIRATION = 1000 * 60 * 60 * 24;

    public String generateToken(User user) {
        return Jwts.builder().setSubject(user.getName()).claim("role", user.getRole()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(secretKey).compact();
    }

    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public String extractUserName(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            extractUserName(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
