package com.example.mailsender.service;

import com.example.mailsender.model.RegistrationDto;
import com.example.mailsender.model.User;
import com.example.mailsender.model.UserRepository;
import com.example.mailsender.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Value("${mail-sender.url}")
    private String url;

    public void addUser(RegistrationDto registrationDto) throws Exception {

        if (StringUtils.isEmpty(registrationDto.getEmail()))
            throw new IllegalArgumentException("Поле email не заполнено");

        if (StringUtils.isEmpty(registrationDto.getUsername()))
            throw new IllegalArgumentException("Поле username не заполнено");

        if (StringUtils.isEmpty(registrationDto.getPassword()))
            throw new IllegalArgumentException("Поле password не заполнено");

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

        String message = String.format(
                "Hello, %s welcome to Mail Sender App \n" +
                        "please visit next link:\n" +
                        url + "/activate/%s",
                user.getUsername(),
                user.getEmailActivationCode());

        mailSender.send(
                user.getEmail(),
                "Activation code",
                message
        );

        userService.createNewUser(user);

        System.out.println("{EQ");
    }

    public void activateUser(String code) throws IllegalArgumentException{

        User user = Optional.ofNullable(userRepository.findByEmailActivationCode(code)).orElseThrow(() ->
                new IllegalArgumentException("Пользователь не найден")
        );

        user.setEmailActivationCode(null);
        userRepository.save(user);
    }
}
