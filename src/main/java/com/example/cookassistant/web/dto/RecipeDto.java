package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.reciepe.Recipe;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecipeDto {

    @Data
    public static class SaveRequestDto {
        private Long userId;
        private String name;
        private String content;
        private String imageURL;
        private LocalDateTime createdAt; // 빼야 하는거 아닌가? , 프론트한테 시각을 받을 일이 없음


    }


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
    public static class FindResponseDto {
        private Long recipeId;
        private Long userId;
        private String name;
        private String content;
        private String imageURL;
        private LocalDateTime createdAt;


        @Builder
        public FindResponseDto(Recipe recipe) {

            this.recipeId = recipe.getId();
            this.userId = recipe.getUser().getId();
            this.name = recipe.getName();
            this.content = recipe.getContent();
            this.imageURL = recipe.getImageURL();
            this.createdAt = recipe.getCreatedAt();


        }
    }

    @Data
    public static class UpdateResponseDto {
       private Long id;
       private String message;

       @Builder
       public UpdateResponseDto(Long id, String message) {
            this.id = id;
            this.message = message;
        }



    }

    @Data
    public static class UpdateRequestDto {
        private Long recipeId;
        private String name;
        private String content;
        private String imageURL;
        private LocalDateTime createdAt;
    }


    @Data
    public static class DeleteResponseDto {
        private String message;
    }

    @Data
    public static class DeleteRequestDto {
        private Long userId;
        private Long recipeId;
    }
}