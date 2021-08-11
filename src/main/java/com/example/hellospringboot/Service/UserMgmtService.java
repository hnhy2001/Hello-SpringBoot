package com.example.hellospringboot.service;

import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.model.User;

import java.util.List;
import java.util.Locale;

public interface UserMgmtService {

    public String createrUser(CreateUserRequest userRequest);

    public UserDto updateUser(int id, UpdateUserRequest updateUserRequest, Locale locale);

    public String deleteUser(int id, Locale locale);

    public List<User> getAllByConditions(Integer id, String email, String firstName, String lastName, String address,
                                         String phone, String role, Locale locale);

    public String login(String email, String pass, Locale locale);


}
