package com.devvictor.spring_boot_refresh_token.dtos;

public record CreateUserDto(
        String email,
        String password,
        String username
) {
}
