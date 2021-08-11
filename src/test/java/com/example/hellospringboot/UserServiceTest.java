package com.example.hellospringboot;

import com.example.hellospringboot.repository.CustomUserRepository;
import com.example.hellospringboot.repository.UserRepository;
import com.example.hellospringboot.service.UserMgmtServiceImpl;
import com.example.hellospringboot.controller.request.FindUserRequest;
import com.example.hellospringboot.jwt.JwtTokenProvider;
import com.example.hellospringboot.model.User;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {


    @MockBean
    UserRepository userRepository;

    @MockBean
    JwtTokenProvider jwtTokenProvider;

    @MockBean
    CustomUserRepository customUserRepository;

    @MockBean
    FindUserRequest findUserRequest;

    @Autowired
    UserMgmtServiceImpl userMgmtService;

    String email = "email@demo";
    String pass = "Pass@demo";
    User user = new User();
    List<User> userList = new ArrayList<>();
    Locale locale ;


    @SneakyThrows
    @Test
    public void loginTestSuccess(){

        when(userRepository.findAllByEmailAndPassword(email, pass)).thenReturn(user);
        when(jwtTokenProvider.generateToken(user)).thenReturn("test thanh cong");

        assertEquals("test thanh cong" , userMgmtService.login(email, pass, locale));
    }

    @SneakyThrows
    @Test
    public void getAllByConditionsTestSuccess(){
        userList.add(user);
        when(customUserRepository.findAllByConditions(FindUserRequest.builder().build())).thenReturn(userList);
        assertEquals(userList, userMgmtService.getAllByConditions(null, null, null, null, null, null, null, locale));
    }







}
