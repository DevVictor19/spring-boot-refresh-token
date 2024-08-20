package com.devvictor.spring_boot_refresh_token.services;

import com.devvictor.spring_boot_refresh_token.entities.Permission;
import com.devvictor.spring_boot_refresh_token.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public Permission findByName(String name) {
        return permissionRepository.findByName(name);
    }
}
