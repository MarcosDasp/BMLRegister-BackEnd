package com.bmlregister.formularios.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import java.security.Key;

public class JwtUtil {

    private static final String SECRET = "uma_chave_secreta_bem_grande_para_token_jwt_seguro_123";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    public static String gerarToken(String login, String nivelAcesso) {
        return Jwts.builder()
                .setSubject(login)
                .claim("role", nivelAcesso)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY)
                .compact();
    }

    public static Jws<Claims> validarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);
    }

    public static String getLoginDoToken(String token) {
        return validarToken(token).getBody().getSubject();
    }
}