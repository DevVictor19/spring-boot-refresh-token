package com.devvictor.spring_boot_refresh_token.services;

import com.devvictor.spring_boot_refresh_token.dtos.CreateUserRequestDto;
import com.devvictor.spring_boot_refresh_token.entities.Permission;
import com.devvictor.spring_boot_refresh_token.entities.User;
import com.devvictor.spring_boot_refresh_token.exceptions.InternalServerErrorException;
import com.devvictor.spring_boot_refresh_token.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionService permissionService;

    public void create(CreateUserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUsername(dto.username());

        Permission permission = permissionService.findByName("CLIENT");

        if (permission == null) {
            throw new InternalServerErrorException("CLIENT permission not defined");
        }

        Set<Permission> permissions = user.getPermissions();
        permissions.add(permission);

        userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
      return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
