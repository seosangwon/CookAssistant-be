package com.example.cookassistant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class CookAssistantApplication {


    public static void main(String[] args) {
        SpringApplication.run(CookAssistantApplication.class, args);


    }




}
