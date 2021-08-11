package com.example.hellospringboot.controller;


import com.example.hellospringboot.Service.DemoService;
import com.example.hellospringboot.Service.MessagesouceService;
import com.example.hellospringboot.Service.UserMgmtService;
import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.LoginUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    /*
    -Hàm này dùng để biến 1 file Json thành 1 đối tượng CreateUserRequest
    -Sau đó truyền đối tượng vừa được tạo từ filejson vào method createrUser thông qua userMgmtService
     */
    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest userRequest) {
        log.info("Đang gọi đến phương thức createUser thông qua đối tượng userMgmtService");
        return new ResponseEntity<String>(userMgmtService.createrUser(userRequest), HttpStatus.OK);
    }


    /*
    Gọi hàm này để update User thông qua id và từ file json thuộc tính muốn update
     */
    @PreAuthorize("hasAuthority('admin')")
    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable(name = "id") int id,
                                              @RequestBody UpdateUserRequest updateUserRequest,
                                              @RequestHeader(name = "langue", required = false) Locale locale) {
        log.info("Đang gọi đến phương thức updateUser thông qua đối tượng userMgmtService");
        return new ResponseEntity<UserDto>(userMgmtService.updateUser(id, updateUserRequest, locale), HttpStatus.OK);

    }


    /*
    xóa user theo id
     */
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id,
                                             @RequestHeader(name = "langue", required = false) Locale locale) {
        log.info("Đang gọi đến phương thức deleteUser thông qua đối tượng userMgmtService");
        return new ResponseEntity<>(userMgmtService.deleteUser(id, locale), HttpStatus.OK);
    }

    //tìm kiếm user theo các thuộc tính của họ
    @PreAuthorize("hasAnyAuthority('user', 'admin')")
    @GetMapping(value = "/find")
    public ResponseEntity<List<User>> findAllByConditions(@RequestParam(name = "id", required = false) Integer id,
                                                          @RequestParam(name = "email", required = false) String email,
                                                          @RequestParam(name = "first_name", required = false) String firstName,
                                                          @RequestParam(name = "last_name", required = false) String lastName,
                                                          @RequestParam(name = "address", required = false) String address,
                                                          @RequestParam(name = "phone", required = false) String phone,
                                                          @RequestParam(name = "role", required = false) String role,
                                                          @RequestHeader(name = "langue", required = false) Locale locale) {
        log.info("Đang gọi đến phương thức getAllByCondittions thông qua đối tượng userMgmtService");
        return new ResponseEntity<>(userMgmtService.getAllByConditions(id, email, firstName, lastName, phone, address, role, locale), HttpStatus.OK);
    }

    //Đăng nhập để lấy ra Beartoken dùng để thực hiện các hàm cần được sự cho phép
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserRequest loginUserRequest,
                                        @RequestHeader(name = "langue", required = false) Locale locale) {
        log.info("Đang gọi đến phương thức loginUser thông qua đối tượng userMgmtService");
        return new ResponseEntity<String>(userMgmtService.login(loginUserRequest.getEmail(), loginUserRequest.getPassword(), locale), HttpStatus.OK);
    }

}
