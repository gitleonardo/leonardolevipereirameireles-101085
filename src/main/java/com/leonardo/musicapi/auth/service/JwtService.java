package com.leonardo.musicapi.auth.service;

import com.leonardo.musicapi.auth.config.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final SecurityProperties props;
    private final SecretKey key;

    public JwtService(SecurityProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.getJwt().getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String gerarAccessToken(String username) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getJwt().getAccessTokenMinutes() * 60L);

        return Jwts.builder()
                .issuer(props.getJwt().getIssuer())
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(Map.of("type", "access"))
                .signWith(key)
                .compact();
    }

    public String gerarRefreshToken(String username) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getJwt().getRefreshTokenMinutes() * 60L);

        return Jwts.builder()
                .issuer(props.getJwt().getIssuer())
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .claims(Map.of("type", "refresh"))
                .signWith(key)
                .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isRefreshToken(String token) {
        Claims c = parseClaims(token);
        Object type = c.get("type");
        return type != null && "refresh".equals(type.toString());
    }

    public String extrairUsername(String token) {
        return parseClaims(token).getSubject();
    }
}
