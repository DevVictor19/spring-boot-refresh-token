package com.devvictor.spring_boot_refresh_token.dtos;

public record RefreshTokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
