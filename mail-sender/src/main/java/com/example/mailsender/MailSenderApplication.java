package com.example.mailsender;

import com.example.mailsender.model.User;
import com.example.mailsender.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MailSenderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MailSenderApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Override
	public void run(String... args) throws Exception {
		userService.createNewUser(new User("123", null,"123","123"));
	}
}
