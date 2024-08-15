package com.devvictor.spring_boot_refresh_token.repositories;

import com.devvictor.spring_boot_refresh_token.entities.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRefreshTokenRepository extends
        JpaRepository<UserRefreshToken, Long> {}