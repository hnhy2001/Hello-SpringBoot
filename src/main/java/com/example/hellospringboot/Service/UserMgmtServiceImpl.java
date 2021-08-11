package com.example.hellospringboot.Service;

import com.example.hellospringboot.RepoSitory.CustomUserRepository;
import com.example.hellospringboot.RepoSitory.RoleRepository;
import com.example.hellospringboot.RepoSitory.UserRepository;
import com.example.hellospringboot.controller.request.CreateUserRequest;
import com.example.hellospringboot.controller.request.FindUserRequest;
import com.example.hellospringboot.controller.request.UpdateUserRequest;
import com.example.hellospringboot.dto.Map;
import com.example.hellospringboot.dto.UserDto;
import com.example.hellospringboot.exceptions.FindNotFound;
import com.example.hellospringboot.exceptions.NotFoundException;
import com.example.hellospringboot.jwt.JwtTokenProvider;
import com.example.hellospringboot.model.Role;
import com.example.hellospringboot.model.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Data
public class UserMgmtServiceImpl implements UserMgmtService {

    private static final Logger log = LoggerFactory.getLogger(UserMgmtServiceImpl.class);

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
        if (Pattern.matches(patternStr, userRequest.getPassword())) {
            User user = map.mapUser(userRequest);
            user.setRole(role);
            userRepository.save(user);
            log.info("Tạo user mới thành công");
            return "Create user success!";
        } else {
            log.warn("Không thể tạo user này!");
            return "Mật khẩu phải ......";
        }
    }

    @Override
    public UserDto updateUser(int id, UpdateUserRequest updateUserRequest, Locale locale) {
        User user = userRepository.getById(id);
        if (user == null) {
            log.info("Không thể update User này");
            throw new FindNotFound(messagesouceService.getMess(locale, "find.mess"));
        }
        if (updateUserRequest.getEmail() != null)
            user.setEmail(updateUserRequest.getEmail());
        if (updateUserRequest.getFirstName() != null)
            user.setFirstName(updateUserRequest.getFirstName());
        if (updateUserRequest.getLastName() != null)
            user.setLastName(updateUserRequest.getLastName());
        if (updateUserRequest.getPhone() != null)
            user.setPhone(updateUserRequest.getPhone());
        if (updateUserRequest.getPassword() != null)
            user.setPassword(updateUserRequest.getPassword());
        if (updateUserRequest.getIsActive() != null)
            user.setIsActive(updateUserRequest.getIsActive());
        if (updateUserRequest.getRole() != null) {
            Role role = roleRepository.getById(updateUserRequest.getRole());
            user.setRole(role);
        }
        userRepository.save(user);

        Map map = new Map();
        map.mapUserDto(user);

        log.info("Update thành công User");
        return map.mapUserDto(user);
    }

    @Override
    public String deleteUser(int id, Locale locale) {

        Optional<User> userOptional = Optional.of(userRepository.getById(id));
        User user = userOptional.get();
        if (user == null) {
            log.info("Không thể xóa User");
            throw new FindNotFound(messagesouceService.getMess(locale, "find.mess"));
        }
        user.setIsActive(false);
        userRepository.save(user);
        log.info("xóa thành công User");
        return "Delete User " + id + " Succsess";
    }

    @Override
    public List<User> getAllByConditions(Integer id, String email, String firstName, String lastName, String address,
                                         String phone, String role, Locale locale) throws FindNotFound {
        FindUserRequest findUserRequest = FindUserRequest.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phone(phone)
                .role(role).build();
        findUserRequest.setId(id);
        List<User> userList = cus.findAllByConditions(findUserRequest);
        if (userList == null) {
            log.info("Không có user nào tồn tại");
            throw new FindNotFound(messagesouceService.getMess(locale, "find.mess"));
        }
            log.info("In ra danh sách user thỏa mãn");
        return userList;
    }

    @Override
    public String login(String email, String pass, Locale locale) {
        User user = userRepository.findAllByEmailAndPassword(email, pass);
        if (user == null) {
            log.warn("Tên đăng nhập hoặc mật khẩu không chính xác");
            throw new NotFoundException(messagesouceService.getMess(locale, "login.mess"));
        }
            log.info("Đăng nhập thành công");
        return jwtTokenProvider.generateToken(user);
    }

}
