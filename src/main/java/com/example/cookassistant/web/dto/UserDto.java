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
        @NotEmpty
        private String nickName;
        @NotEmpty @Email
        private String email;
        @NotEmpty
        private String password;
        private UserRole role = UserRole.USER;


    }


    @Data
    public static class LoginRequestDto {
        @Email @NotBlank
        private String email;
        @NotBlank
        private String password;


    }

    @Data
    public static class LoginResponseDto {
    }
}
