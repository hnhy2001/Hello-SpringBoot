package com.example.hellospringboot.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindUserRequest {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String role;
}
