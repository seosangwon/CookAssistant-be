package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

public class UserDto {

    @Data
    public static class SaveResponseDto {

        private Long id;


    }

    @Data
    public static class SaveRequestDto {
        @NotBlank(message = "닉네임을 입력해주세요")
        private String nickName;
        @NotBlank @Email(message = "이메일 양식에 맞춰주세요")
        private String email;
        @NotBlank(message = "패스워드를 입력해주세요")
        private String password;
        private UserRole role = UserRole.USER;


    }


    @Data
    public static class LoginRequestDto {
        @Email @NotBlank(message = "email 양식에 맞춰주세요")
        private String email;
        @NotBlank(message = "패스워드를 입력해주세요")
        private String password;


    }

    @Data
    public static class UserDetailsDto {

        private Long userId;
        private String email;

    }
}
