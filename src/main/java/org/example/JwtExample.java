package org.example;

import io.jsonwebtoken.Claims;

public class JwtExample {
    public static void main(String[] args) {
        // JWT 생성
        String token = JwtUtil.createToken("user123");
        System.out.println("Generated Token: " + token);

        // JWT 검증
        Claims claims = JwtUtil.validateToken(token);
        if (claims != null) {
            System.out.println("Token is valid");
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Expiration: " + claims.getExpiration());
        } else {
            System.out.println("Token is invalid");
        }
    }
}
