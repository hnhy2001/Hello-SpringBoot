package com.example.hellospringboot.Service;

import com.example.hellospringboot.RepoSitory.CustomUserRepository;
import com.example.hellospringboot.RepoSitory.RoleRepository;
import com.example.hellospringboot.RepoSitory.UserRepository;
import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.FindUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.Map;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.jwt.JwtTokenProvider;
import com.example.hellospringboot.model.Role;
import com.example.hellospringboot.model.User;
import javassist.NotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Data
public class UserMgmtServiceImpl implements UserMgmtService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CustomUserRepository cus;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    MessagesouceService messagesouceService;


    
    @Override
    public String createrUser(CreateUserRequest userRequest) {

        Role role = roleRepository.getById(userRequest.getRole());
        Map map = new Map();
        String patternStr = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (Pattern.matches(patternStr,userRequest.getPassword())){
            User user = map.mapUser(userRequest);
            user.setRole(role);
            userRepository.save(user);
            return "Create user success!";
        }
        else {
            return "Mật khẩu phải ......";
        }
    }

    @Override
    public UserDto updateUser(int id, UpdateUserRequest updateUserRequest) throws Exception {
        User user = userRepository.getById(id);
        if (user == null){
            throw new Exception("User not found!");
        }

        if(updateUserRequest.getEmail() != null)
            user.setEmail(updateUserRequest.getEmail());
        if(updateUserRequest.getFirstName() != null)
            user.setFirstName(updateUserRequest.getFirstName());
        if(updateUserRequest.getLastName() != null)
            user.setLastName(updateUserRequest.getLastName());
        if(updateUserRequest.getPhone() != null)
            user.setPhone(updateUserRequest.getPhone());
        if (updateUserRequest.getPassword() != null)
            user.setPassword(updateUserRequest.getPassword());
        if (updateUserRequest.getIsActive() != null)
            user.setIsActive(updateUserRequest.getIsActive());
        if (updateUserRequest.getRole() != null){
            Role role = roleRepository.getById(updateUserRequest.getRole());
            user.setRole(role);
        }

        userRepository.save(user);

        Map map = new Map();
        map.mapUserDto(user);

        return map.mapUserDto(user);
    }

    @Override
    public String deleteUser(int id) throws Exception {

        Optional<User> userOptional = Optional.of(userRepository.getById(id));
        if (userOptional == null) {
            throw  new Exception("Not found user!");
        }
        User user = userOptional.get();
            user.setIsActive(false);
            userRepository.save(user);
            return "Delete User " + id + " Succsess";
    }

    @Override
    public List<User> getAllByConditions(Integer id, String email, String firstName, String lastName, String address,
                                         String phone, String role) throws Exception{
        FindUserRequest findUserRequest = FindUserRequest.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .role(role).build();
        findUserRequest.setId(id);

        List<User> userList = cus.findAllByConditions(findUserRequest);
        if (userList == null){
            throw new Exception("not found!");
        }

        return userList;
    }

    @Override
    public String login(String email, String pass) throws Exception{
        User user = userRepository.findAllByEmailAndPassword(email, pass);
        if (user == null){
            throw new Exception("not found!");
        }

        return jwtTokenProvider.generateToken(user);
    }

}
