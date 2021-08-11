package com.example.hellospringboot.repository;

import com.example.hellospringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findAllByEmailAndPassword(String email, String password);

    User findAllByEmail(String email);

    List<User> findAllByFirstName(String firstName);

}
