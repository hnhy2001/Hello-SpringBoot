package com.example.hellospringboot.controller;


import com.example.hellospringboot.Service.DemoService;
import com.example.hellospringboot.Service.MessagesouceService;
import com.example.hellospringboot.Service.UserMgmtService;
import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.LoginUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMgmtService userMgmtService;

    @Autowired
    MessagesouceService messagesouceService;

    @Autowired
    DemoService demoService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest userRequest){
        return new ResponseEntity<String>(userMgmtService.createrUser(userRequest), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('admin')")
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") int id,
                                              @RequestBody UpdateUserRequest updateUserRequest,
                                              @RequestHeader(name = "langue", required = false)Locale locale){
        try{
            return new ResponseEntity<UserDto>(userMgmtService.updateUser(id, updateUserRequest), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id,
                                             @RequestHeader(name = "langue", required = false)Locale locale) {
        try{
        return new ResponseEntity<>(userMgmtService.deleteUser(id), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(messagesouceService.getMess(locale, "delete.mess"), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    @GetMapping(value = "/find")
    public ResponseEntity<List<User>> findAllByConditions(@RequestParam(name = "id" , required = false) Integer id,
                                                          @RequestParam(name = "email", required = false) String email,
                                                          @RequestParam(name = "first_name", required = false) String firstName,
                                                          @RequestParam(name = "last_name", required = false) String lastName,
                                                          @RequestParam(name = "address", required = false) String address,
                                                          @RequestParam(name = "phone", required = false) String phone,
                                                          @RequestParam(name = "role", required = false) String role){
        try {
            return new ResponseEntity<>(userMgmtService.getAllByConditions(id, email, firstName, lastName, phone, address, role),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserRequest loginUserRequest,
                                        @RequestHeader(name = "langue", required = false)Locale locale){
        try{
            return new ResponseEntity<String>(userMgmtService.login(loginUserRequest.getEmail(), loginUserRequest.getPassword()),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(messagesouceService.getMess(locale,"login.mess"), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/pass")
    public String testPass (@RequestParam(name = "pass") String pass){
        return demoService.demoPass(pass);
    }

}
