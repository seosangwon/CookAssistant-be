package com.example.cookassistant.base;

import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRole;
import com.example.cookassistant.domain.user.UserService;
import com.example.cookassistant.web.dto.UserDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile(("test"))
public class TestInitData {
    @Bean
    CommandLineRunner initData(UserService userService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        UserDto.SaveRequestDto requestDto1 = new UserDto.SaveRequestDto();
        UserDto.SaveRequestDto requestDto2 = new UserDto.SaveRequestDto();

        requestDto1.setEmail("email1");
        requestDto1.setPassword(password);
        requestDto1.setNickName("nickname1");
        requestDto1.setRole(UserRole.USER);

        requestDto2.setEmail("email2");
        requestDto2.setPassword(password);
        requestDto2.setNickName("nickname2");
        requestDto2.setRole(UserRole.USER);



        return args -> {
            UserDto.SaveResponseDto responseDto1 = userService.createUser(requestDto1);
            UserDto.SaveResponseDto responseDto2 = userService.createUser(requestDto2);

        };
    }
}
