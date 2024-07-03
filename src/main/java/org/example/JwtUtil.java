package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SecurityException;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY_STRING = "fewkfnklewnfewkfewlkfnlewnfklewnfwelfnewiofnewiofnoiwenfiwenfiowenfioewnfwekfwenflkewnlkfewfwekf";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_STRING));
    private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    // JWT 생성 메서드
    public static String createToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 검증 메서드
    public static Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SecurityException e) {
            System.out.println("Invalid JWT signature");
            return null;
        } catch (Exception e) {
            System.out.println("Invalid JWT token");
            return null;
        }
    }

    // JWT에서 subject 추출 메서드
    public static String getSubject(String token) {
        Claims claims = validateToken(token);
        return claims != null ? claims.getSubject() : null;
    }
}
