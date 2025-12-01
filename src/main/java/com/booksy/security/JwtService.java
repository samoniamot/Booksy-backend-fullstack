package com.booksy.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * servicio para manejar los tokens jwt
 * genera valida y extrae informacion de los tokens
 */
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secreto;
    
    @Value("${jwt.expiration}")
    private long expiracion;
    
    // genera un token para el usuario
    public String generarToken(String email, String rol, String id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", rol);
        claims.put("id", id);
        return crearToken(claims, email);
    }
    
    // crea el token con los claims y el subject
    private String crearToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(obtenerClave())
                .compact();
    }
    
    // obtiene la clave secreta para firmar
    private SecretKey obtenerClave() {
        byte[] keyBytes = secreto.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    // extrae el email del token
    public String extraerEmail(String token) {
        return extraerClaim(token, Claims::getSubject);
    }
    
    // extrae el rol del token
    public String extraerRol(String token) {
        return extraerClaim(token, claims -> claims.get("rol", String.class));
    }
    
    // extrae el id del token
    public String extraerId(String token) {
        return extraerClaim(token, claims -> claims.get("id", String.class));
    }
    
    // extrae la fecha de expiracion
    public Date extraerExpiracion(String token) {
        return extraerClaim(token, Claims::getExpiration);
    }
    
    // extrae un claim especifico
    public <T> T extraerClaim(String token, Function<Claims, T> resolver) {
        final Claims claims = extraerTodosClaims(token);
        return resolver.apply(claims);
    }
    
    // extrae todos los claims del token
    private Claims extraerTodosClaims(String token) {
        return Jwts.parser()
                .verifyWith(obtenerClave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    // verifica si el token expiro
    private boolean tokenExpirado(String token) {
        return extraerExpiracion(token).before(new Date());
    }
    
    // valida el token comparando el email
    public boolean validarToken(String token, String email) {
        final String emailToken = extraerEmail(token);
        return (emailToken.equals(email) && !tokenExpirado(token));
    }
}
