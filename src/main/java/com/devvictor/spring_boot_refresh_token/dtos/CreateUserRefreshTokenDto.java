package com.devvictor.spring_boot_refresh_token.dtos;

import com.devvictor.spring_boot_refresh_token.entities.User;

public record CreateUserRefreshTokenDto(
        User user,
        String refreshToken
) {
}
