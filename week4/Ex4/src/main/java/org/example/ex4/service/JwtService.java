package org.example.ex4.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.ex4.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class JwtService {
    public String generateToken(User user, boolean isAccessToken, int expTime, String key) {
        var builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expTime * 1000))
                .setSubject(user.getUsername());

        if (isAccessToken) builder.claim("scope", user.getRole());

        return builder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

    private Claims extractAllClaims(String token,String key) {
        return Jwts.parser().setSigningKey(getSignKey(key))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String key) {
        final Claims claims = extractAllClaims(token,key);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token, String key) {
        return extractClaim(token, Claims::getSubject , key);
    }

    public Date extractExpiration(String token, String key) {
        return extractClaim(token, Claims::getExpiration, key);
    }

    public Boolean validateToken(String token, UserDetails userDetails, String key) {
        final String username = extractUsername(token, key);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, key));
    }

    public Boolean isTokenExpired(String token, String key) {
        final Date expiration = extractExpiration(token , key);
        return expiration.before(new Date());
    }

    private Key getSignKey(String key) {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
