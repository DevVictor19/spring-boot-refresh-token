package com.devvictor.spring_boot_refresh_token.repositories;

import com.devvictor.spring_boot_refresh_token.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
}
