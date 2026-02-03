package com.leonardo.musicapi.auth.service;

import com.leonardo.musicapi.auth.config.SecurityProperties;
import com.leonardo.musicapi.auth.dto.RefreshRequest;
import com.leonardo.musicapi.auth.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SecurityProperties props;

    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService, SecurityProperties props) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.props = props;
    }

    public TokenResponse login(String username, String password) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        String access = jwtService.gerarAccessToken(auth.getName());
        String refresh = jwtService.gerarRefreshToken(auth.getName());

        long expiresIn = props.getJwt().getAccessTokenMinutes() * 60L;
        return new TokenResponse("Bearer", access, refresh, expiresIn);
    }

    public TokenResponse refresh(RefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        if (!jwtService.isRefreshToken(refreshToken)) {
            throw new IllegalArgumentException("Token de refresh inválido");
        }

        Claims claims = jwtService.parseClaims(refreshToken); // valida assinatura/expiração
        String username = claims.getSubject();

        // Rotaciona refresh token (boa prática)
        String newAccess = jwtService.gerarAccessToken(username);
        String newRefresh = jwtService.gerarRefreshToken(username);

        long expiresIn = props.getJwt().getAccessTokenMinutes() * 60L;
        return new TokenResponse("Bearer", newAccess, newRefresh, expiresIn);
    }
}
