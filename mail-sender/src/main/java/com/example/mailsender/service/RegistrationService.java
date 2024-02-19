package com.example.mailsender.service;

import com.example.mailsender.model.RegistrationDto;
import com.example.mailsender.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    public void addUser(RegistrationDto registrationDto) throws Exception{

        if (!Objects.equals(registrationDto.getPassword(), registrationDto.getConfirmPassword()))
            throw new IllegalArgumentException("Пароль и подтверждение пароля не совпадают");

        if (userService.findByUsername(registrationDto.getUsername()).isPresent())
            throw new IllegalArgumentException("Пользователь уже существует");

        User user = new User(
                registrationDto.getEmail(),
                UUID.randomUUID().toString(),
                registrationDto.getUsername(),
                registrationDto.getPassword()
        );

        userService.createNewUser(user);
    }
}
