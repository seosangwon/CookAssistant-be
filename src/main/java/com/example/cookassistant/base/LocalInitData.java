package com.example.cookassistant.base;

import com.example.cookassistant.domain.user.UserRole;
import com.example.cookassistant.domain.user.UserService;
import com.example.cookassistant.web.dto.UserDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("local")
public class LocalInitData {

    @Bean
    CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        String password = "1234";
        UserDto.SaveRequestDto requestDto1 = new UserDto.SaveRequestDto();
        UserDto.SaveRequestDto requestDto2 = new UserDto.SaveRequestDto();

        requestDto1.setEmail("user1@naver.com");
        requestDto1.setPassword(password);
        requestDto1.setNickName("nickname1");





        requestDto2.setEmail("user2@naver.com");
        requestDto2.setPassword(password);
        requestDto2.setNickName("nickname2");




        return args -> {
            UserDto.SaveResponseDto responseDto1 = userService.createUser(requestDto1);
            UserDto.SaveResponseDto responseDto2 = userService.createUser(requestDto2);

        };
    }

}
