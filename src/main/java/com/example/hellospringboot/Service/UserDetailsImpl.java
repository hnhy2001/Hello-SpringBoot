package com.example.hellospringboot.Service;

import com.example.hellospringboot.RepoSitory.UserRepository;
import com.example.hellospringboot.details.CustomUserDetails;
import com.example.hellospringboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findAllByEmail(username);
        if (user != null)
            return new CustomUserDetails(user);
        return null;
    }
}
