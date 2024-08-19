package com.devvictor.spring_boot_refresh_token.services;

import com.devvictor.spring_boot_refresh_token.dtos.CreateUserRefreshTokenDto;
import com.devvictor.spring_boot_refresh_token.entities.UserRefreshToken;
import com.devvictor.spring_boot_refresh_token.repositories.UserRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRefreshTokenService {

    @Autowired
    UserRefreshTokenRepository userRefreshTokenRepository;

    public void save(CreateUserRefreshTokenDto dto) {
        Optional<UserRefreshToken> userRefreshToken = userRefreshTokenRepository
                .findById(dto.user().getId());

        UserRefreshToken entity;

        if (userRefreshToken.isPresent()) {
            entity = userRefreshToken.get();
        } else {
            entity = new UserRefreshToken();
            entity.setId(dto.user().getId());
            entity.setUser(dto.user());
        }

        entity.setRefreshToken(dto.refreshToken());
        userRefreshTokenRepository.save(entity);
    }
}
