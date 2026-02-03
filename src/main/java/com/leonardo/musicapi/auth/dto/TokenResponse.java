package com.leonardo.musicapi.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TokenResponse")
public record TokenResponse(
        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String accessToken,

        @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String refreshToken
) {}
