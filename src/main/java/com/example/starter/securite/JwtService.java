package com.example.starter.securite;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final SecretKey cle;
    private final long dureeExpirationMs;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms}") long dureeExpirationMs) {
        this.cle = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.dureeExpirationMs = dureeExpirationMs;
    }

    public String genererToken(UserDetails userDetails) {
        Date maintenant = new Date();
        Date expiration = new Date(maintenant.getTime() + dureeExpirationMs);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .issuedAt(maintenant)
                .expiration(expiration)
                .signWith(cle)
                .compact();
    }

    public String extraireEmail(String token) {
        return extraireToutesLesClaims(token).getSubject();
    }

    public boolean estValide(String token, UserDetails userDetails) {
        String email = extraireEmail(token);
        return email.equals(userDetails.getUsername()) && !estExpire(token);
    }

    private boolean estExpire(String token) {
        return extraireToutesLesClaims(token).getExpiration().before(new Date());
    }

    private io.jsonwebtoken.Claims extraireToutesLesClaims(String token) {
        return Jwts.parser()
                .verifyWith(cle)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}