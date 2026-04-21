package org.example.gimnasio.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Service
public class JwtService {

    // 🔐 CLAVE (debe ser suficientemente larga)
    private static final String SECRET =
            Base64.getEncoder().encodeToString(
                    "mi_clave_muy_segura_para_jwt_1234567890".getBytes()
            );

    private final Key key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    // ==========================
    // GENERAR TOKEN
    // ==========================
    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ==========================
    // EXTRAER EMAIL
    // ==========================
    public String extractEmail(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // ==========================
    // VALIDAR TOKEN
    // ==========================
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}