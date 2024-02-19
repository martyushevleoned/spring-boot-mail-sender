package com.example.mailsender.service;

import com.example.mailsender.model.Role;
import com.example.mailsender.model.User;
import com.example.mailsender.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createNewUser(User user){
        user.setEmailActivationCode(null);
        user.setRoles(Set.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
