package com.devvictor.spring_boot_refresh_token.services;

import com.devvictor.spring_boot_refresh_token.dtos.*;
import com.devvictor.spring_boot_refresh_token.entities.User;
import com.devvictor.spring_boot_refresh_token.exceptions.BadRequestException;
import com.devvictor.spring_boot_refresh_token.exceptions.UnauthorizedException;
import com.devvictor.spring_boot_refresh_token.providers.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void signup(SignupUserRequestDto dto) {
        Optional<User> user = userService.findByEmail(dto.email());

        if (user.isPresent()) {
            throw new BadRequestException("Email already in use");
        }

        String hashedPwd = passwordEncoder.encode(dto.password());

        CreateUserRequestDto createUserDto = new CreateUserRequestDto(
                dto.email(),
                hashedPwd,
                dto.username()
        );

        userService.create(createUserDto);
    }

    public LoginUserResponseDto login(LoginUserRequestDto dto) {
        Optional<User> user = userService.findByEmail(dto.email());

        if (user.isEmpty()) {
            throw new UnauthorizedException();
        }

        boolean isValidPwd = passwordEncoder.matches(dto.password(), user.get().getPassword());

        if (!isValidPwd) {
            throw new UnauthorizedException();
        }

        return new LoginUserResponseDto(
                jwtProvider.generateAccessToken(user.get()),
                jwtProvider.generateRefreshToken(user.get())
        );
    }
}
