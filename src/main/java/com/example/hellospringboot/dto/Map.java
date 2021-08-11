package com.example.hellospringboot.dto;

import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.model.User;

public class Map {

    public User mapUser(CreateUserRequest userRequest) {
        return User.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .isActive(userRequest.getIsActive())
                .password(userRequest.getPassword())
                .build();
    }

    public UserDto mapUserDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword()).build();
    }

}
