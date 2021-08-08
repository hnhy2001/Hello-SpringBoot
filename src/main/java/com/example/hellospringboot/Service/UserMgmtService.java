package com.example.hellospringboot.Service;

import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.FindUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserMgmtService {

    public String createrUser(CreateUserRequest userRequest);

    public UserDto updateUser(int id, UpdateUserRequest updateUserRequest) throws Exception;

    public String deleteUser(int id) throws Exception;

    public List<User> getAllByConditions(Integer id, String email, String firstName, String lastName, String address,
                                         String phone, String role) throws Exception;

    public String login(String email, String pass) throws Exception;


}
