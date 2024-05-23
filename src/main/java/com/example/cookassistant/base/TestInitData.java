package com.example.cookassistant.base;

import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRole;
import com.example.cookassistant.domain.user.UserService;
import com.example.cookassistant.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@Profile(("test"))
public class TestInitData {
    @Bean
    CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        String plainPassword="1234";
        //String password = passwordEncoder.encode(plainPassword);
        UserDto.SaveRequestDto requestDto1 = new UserDto.SaveRequestDto();
        UserDto.SaveRequestDto requestDto2 = new UserDto.SaveRequestDto();

        requestDto1.setEmail("user1@naver.com");
        requestDto1.setPassword(plainPassword);
        requestDto1.setNickName("nickname1");
        requestDto1.setRole(UserRole.USER);




        requestDto2.setEmail("user2@naver.com");
        requestDto2.setPassword(plainPassword);
        requestDto2.setNickName("nickname2");
        requestDto2.setRole(UserRole.USER);



        return args -> {
            UserDto.SaveResponseDto responseDto1 = userService.createUser(requestDto1);
            UserDto.SaveResponseDto responseDto2 = userService.createUser(requestDto2);

        };
    }
}
