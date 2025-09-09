package com.example.tanmay.library_management.Utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    static String SECRET = "vN8aZf2tQxKkN9pPsh9z7kdq3Fq0wW6hZ3H2kJqX2A8=";
    static int EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    // Methods for generating, validating, and parsing JWTs would go here
    public static String generateToken(UserDetails userDetails) {
        // Implementation for generating a JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(",")));

        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET)
            .compact();
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        // Implementation for validating a JWT
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private static boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public static String extractUsername(String token) {
        // Implementation for extracting the username from a JWT
        return extractAllClaims(token).getSubject();
    }

    public static String extractRole(String token) {
        // Implementation for extracting the role from a JWT
        return extractAllClaims(token).get("role", String.class);
    }

    public static Claims extractAllClaims(String token) {
        return Jwts.parser()
            .setSigningKey(SECRET)
            .parseClaimsJws(token)
            .getBody();
    }
}
