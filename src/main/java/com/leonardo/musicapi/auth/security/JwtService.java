package com.leonardo.musicapi.auth.security;

import com.leonardo.musicapi.auth.config.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final SecurityProperties props;
    private final SecretKey key;

    public JwtService(SecurityProperties props) {
        this.props = props;
        this.key = Keys.hmacShaKeyFor(props.getJwt().getSecret().getBytes(StandardCharsets.UTF_8));
    }

    public String gerarAccessToken(String username, List<String> roles) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getJwt().getAccessTokenMinutes() * 60);

        return Jwts.builder()
                .issuer(props.getJwt().getIssuer())
                .subject(username)
                .claim("roles", roles)
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public String gerarRefreshToken(String username) {
        Instant now = Instant.now();
        Instant exp = now.plusSeconds(props.getJwt().getRefreshTokenMinutes() * 60);

        return Jwts.builder()
                .issuer(props.getJwt().getIssuer())
                .subject(username)
                .claim("type", "refresh")
                .issuedAt(Date.from(now))
                .expiration(Date.from(exp))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Jws<Claims> validar(String token) {
        return Jwts.parser()
                .requireIssuer(props.getJwt().getIssuer())
                .verifyWith((javax.crypto.SecretKey) key)
                .build()
                .parseSignedClaims(token);
    }

     public Claims validarRefreshEObterClaims(String refreshToken) {
        Jws<Claims> jws = validar(refreshToken);
        Claims claims = jws.getPayload(); // (substitui o getBody/getPayload conforme sua lib)

        Object type = claims.get("type");
        if (!"refresh".equals(String.valueOf(type))) {
            throw new JwtException("Token não é refresh");
        }
        return claims;
    }
}
