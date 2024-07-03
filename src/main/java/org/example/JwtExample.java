package org.example;

import java.util.HashMap;
import java.util.Map;

public class JwtExample {
    public static void main(String[] args) {
        // 클레임을 포함한 Map 생성
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", "user123");
        claims.put("email", "user@example.com");
        claims.put("nickname", "nickname123");

        // JWT 생성
        String token = JwtUtil.createToken(claims);
        System.out.println("Generated Token: " + token);

        // JWT 검증 및 모든 클레임 출력
        JwtUtil.printAllClaims(token);
    }
}
