package com.leonardo.musicapi.auth.api;

import com.leonardo.musicapi.auth.dto.LoginRequest;
import com.leonardo.musicapi.auth.dto.RefreshRequest;
import com.leonardo.musicapi.auth.dto.TokenResponse;
import com.leonardo.musicapi.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Autenticação JWT (login e refresh)")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Login", description = "Gera access token (5 min) e refresh token.")
    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request.getUsername(), request.getPassword());
    }

    @Operation(summary = "Refresh", description = "Gera novo access token e rotaciona o refresh token.")
    @PostMapping("/refresh")
    public TokenResponse refresh(@Valid @RequestBody RefreshRequest request) {
        return authService.refresh(request);
    }
}
