package com.devvictor.spring_boot_refresh_token.services;

import com.devvictor.spring_boot_refresh_token.dtos.CreateUserDto;
import com.devvictor.spring_boot_refresh_token.entities.User;
import com.devvictor.spring_boot_refresh_token.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public void create(CreateUserDto dto) {
        var entity = new User();
        entity.setEmail(dto.email());
        entity.setPassword(dto.password());
        entity.setUsername(dto.username());

        userRepository.save(entity);
    }

    public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
