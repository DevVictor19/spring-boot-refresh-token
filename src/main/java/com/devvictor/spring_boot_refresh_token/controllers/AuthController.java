package com.devvictor.spring_boot_refresh_token.controllers;

import com.devvictor.spring_boot_refresh_token.dtos.LoginUserRequestDto;
import com.devvictor.spring_boot_refresh_token.dtos.LoginUserResponseDto;
import com.devvictor.spring_boot_refresh_token.dtos.SignupUserRequestDto;
import com.devvictor.spring_boot_refresh_token.exceptions.BadRequestException;
import com.devvictor.spring_boot_refresh_token.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupUserRequestDto dto) {
        if (dto == null || dto.email() == null || dto.username() == null || dto.password() == null) {
            throw new BadRequestException("Missing correct signup dto");
        }

        authService.signup(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto dto) {
        if (dto == null || dto.email() == null || dto.password() == null) {
            throw new BadRequestException("Missing correct login dto");
        }

        return ResponseEntity.ok(authService.login(dto));
    }
}
