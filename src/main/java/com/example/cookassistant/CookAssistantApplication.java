package com.example.cookassistant;

import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.domain.user.UserRoll;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class CookAssistantApplication {


    public static void main(String[] args) {
        SpringApplication.run(CookAssistantApplication.class, args);


    }

}
