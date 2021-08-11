package com.example.hellospringboot.repository;

import com.example.hellospringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findAllByName(String roleName);

}
