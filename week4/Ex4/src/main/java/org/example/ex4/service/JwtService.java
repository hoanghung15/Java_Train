package org.example.ex4.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.example.ex4.entity.User;
import org.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class JwtService {
    @NonFinal
    @Value("${jwt.access.key}")
    String accessKey;
    @NonFinal
    @Value("${jwt.refresh.key}")
    String refreshKey;
    @NonFinal
    @Value("${jwt.access.expire}")
    int expAccess;
    @Value("${jwt.refresh.expire}")
    @NonFinal
    int expRefresh;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtService jwtService;

    public String generateToken(User user, boolean isAccessToken, int expTime, String key) {
        var builder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expTime * 1000))
                .setSubject(user.getUsername());

        if (isAccessToken) builder.claim("scope", user.getRole());

        return builder.signWith(SignatureAlgorithm.HS512, key).compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(accessKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
