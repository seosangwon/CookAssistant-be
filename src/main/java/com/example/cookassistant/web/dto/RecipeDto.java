package com.example.cookassistant.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecipeDto {

    public static class SaveRequestDto {
        private String name;
        private String content;
        private String imageURL;
        private LocalDateTime createdAt;



    }



}
