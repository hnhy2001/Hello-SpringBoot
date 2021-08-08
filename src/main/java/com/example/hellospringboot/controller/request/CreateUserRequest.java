package com.example.hellospringboot.controller.request;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateUserRequest {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String password;
    private Boolean isActive = true;
    private String role;

}
