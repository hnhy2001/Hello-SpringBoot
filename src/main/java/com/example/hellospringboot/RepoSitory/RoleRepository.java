package com.example.hellospringboot.RepoSitory;

import com.example.hellospringboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findAllByName(String roleName);

}
