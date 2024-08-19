package com.devvictor.spring_boot_refresh_token.dtos;

public record LoginUserDto(
        String email,
        String password
) {
}
