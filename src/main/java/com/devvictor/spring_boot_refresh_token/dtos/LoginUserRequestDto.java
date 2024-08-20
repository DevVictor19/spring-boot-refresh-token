package com.devvictor.spring_boot_refresh_token.dtos;

public record LoginUserRequestDto(
        String email,
        String password
) {
}
