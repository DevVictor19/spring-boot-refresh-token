package com.devvictor.spring_boot_refresh_token.dtos;

public record SignupUserDto(
        String email,
        String password,
        String username
) {
}
