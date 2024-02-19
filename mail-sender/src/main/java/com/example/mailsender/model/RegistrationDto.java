package com.example.mailsender.model;

import lombok.Data;
import lombok.Getter;

@Data
public class RegistrationDto {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}
