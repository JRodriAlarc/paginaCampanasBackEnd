package com.ArcaDeLaAlianza.ArcaDeLaAlianza.Config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String secret="clavesecreta";

    public String generateToken(String username){

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 3600000))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    // Método para validar el token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Token JWT no válido: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener el nombre de usuario desde el token JWT
    public String extractUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject(); // retorna nombre de usuario
    }

}
