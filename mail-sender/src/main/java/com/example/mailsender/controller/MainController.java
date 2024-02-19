package com.example.mailsender.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;


@Controller
public class MainController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(){
        return "home";
    }

    @GetMapping("/")
    private String root() {
        return "home";
    }

    @GetMapping("/info")
    private String info(@AuthenticationPrincipal UserDetails userDetails, Model model) {

        Map<String, Object> map = new HashMap<>();
        map.put("username", userDetails.getUsername());
        map.put("password", userDetails.getPassword());
        map.put("authorities", userDetails.getAuthorities());
        model.addAllAttributes(map);

        return "info";
    }

}
