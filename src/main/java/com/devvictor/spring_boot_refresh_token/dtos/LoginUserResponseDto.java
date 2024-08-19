package com.devvictor.spring_boot_refresh_token.dtos;

public record LoginUserResponseDto(
        String accessToken,
        String refreshToken
) {
}
