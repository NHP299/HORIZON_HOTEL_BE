package com.horizon.util;

import com.horizon.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;

    @Value("${jwt.keySecret}")
    private String keySecret;

    public String generateToken(Account account) throws InvalidParameterException {
        Map<String, Object> claims = new HashMap<>();
//        this.generateSecretKey();
        claims.put("email", account.getEmail());
        try {
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(account.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSigningKey())
                    .compact();
            return token;

        } catch (Exception e) {
           throw new InvalidParameterException("Invalid token" + e.getMessage());

        }

    }

    private Key getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(keySecret); //MYZLDD5Z0AvcvaA/p9autL/Oq2OBKoFh3VrISdiYuQc=
        return Keys.hmacShaKeyFor(bytes);
    }

    private String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String secretKey = Encoders.BASE64.encode(bytes);
        return secretKey;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        Date date = this.extractClaim(token, Claims::getExpiration);
        return date.before(new Date());
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
       String email = extractEmail(token);
       return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}