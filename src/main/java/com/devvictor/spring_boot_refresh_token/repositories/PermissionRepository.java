package com.devvictor.spring_boot_refresh_token.repositories;

import com.devvictor.spring_boot_refresh_token.entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    public Permission findByName(String name);
}
