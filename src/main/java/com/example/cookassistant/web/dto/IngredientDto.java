package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.ingredient.Ingredient;
import com.example.cookassistant.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

public class IngredientDto {


    @Data
    public static class SaveRequestDto {

        @NotBlank(message = "이름을 입력해주세요")
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        @NotBlank(message = "타입을 입력해주세요")
        private String type;


    }

    @Data
    public static class SaveResponseDto {
        private Long id;
        private String message;

        @Builder
        public SaveResponseDto(Long id) {
            this.id = id;

        }


    }


    @Data
    public static class FindResponseDto {

        private Long id;
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        private String type;

        @Builder
        public FindResponseDto(Ingredient ingredient) {
            this.id = ingredient.getId();
            this.name = ingredient.getName();
            this.quantity = ingredient.getQuantity();
            this.expirationDate = ingredient.getExpirationDate();
            this.imageURL = ingredient.getImageURL();
            this.type = ingredient.getType();
        }


    }

    @Data
    public static class DeleteRequestDto {
        @NotNull(message = "재료Id를 입력해주세요")
        private Long ingredientId;
    }


    @Data
    public static class UpdateRequestDto {
        @NotNull(message = "id를 입력해주세요")
        private Long id;
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        @NotBlank(message = "type을 입력해주세요")
        private String type;

    }


    @Data
    public static class UpdateResponseDto {
        private Long id;

        @Builder
        public UpdateResponseDto(Long id) {
            this.id = id;

        }


    }


}