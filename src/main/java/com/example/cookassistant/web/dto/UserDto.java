package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

public class UserDto {

    @Data
    public static class SaveResponseDto {

        private Long id;
        private String message;

        @Builder
        public SaveResponseDto(Long id, String message) {
            this.id = id;
            this.message = message;
        }

    }

    @Data
    public static class SaveRequestDto {
        private String nickName;
        private String email;
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
