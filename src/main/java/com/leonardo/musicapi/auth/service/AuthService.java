package com.leonardo.musicapi.auth.service;

import com.leonardo.musicapi.auth.dto.TokenResponse;
import com.leonardo.musicapi.auth.security.JwtService;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public TokenResponse refresh(String refreshToken) {
        Claims claims = jwtService.validarRefreshEObterClaims(refreshToken);

        String username = claims.getSubject();

        var user = userDetailsService.loadUserByUsername(username);
        var roles = user.getAuthorities().stream()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .toList();

        String newAccess = jwtService.gerarAccessToken(username, roles);

        // mantém o mesmo refresh (mais simples). Se quiser rotação, eu te passo abaixo.
        return new TokenResponse(newAccess, refreshToken);
    }
}
