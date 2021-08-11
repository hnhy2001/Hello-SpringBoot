package com.example.hellospringboot.repository;

import java.io.Serializable;
import java.util.List;

import com.example.hellospringboot.controller.request.FindUserRequest;
import org.springframework.stereotype.Repository;


public interface CustomUserRepository<User, Integer extends Serializable> extends Repository {
    List<User> findAllByConditions(FindUserRequest findUserRequest);
}