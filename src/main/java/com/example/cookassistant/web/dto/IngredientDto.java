package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.ingredient.Ingredient;
import com.example.cookassistant.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

public class IngredientDto {



    @Data
    public static class SaveRequestDto {

        @NotBlank
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        private Long UserId;
        @NotBlank
        private String type;


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

        private Long id;
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        private String type;

        @Builder
        public FindResponseDto(Ingredient ingredient) {
            this.id= ingredient.getId();
            this.name = ingredient.getName();
            this.quantity = ingredient.getQuantity();
            this.expirationDate = ingredient.getExpirationDate();
            this.imageURL = ingredient.getImageURL();
            this.type = ingredient.getType();
        }




    }

    @Data
    public static class DeleteRequestDto {
        private Long userId;
        private Long ingredientId;
    }


    @Data
    public static class UpdateRequestDto {
        private Long id;
        private String name;
        private String quantity;
        private String expirationDate;
        private String imageURL;
        private String type;

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







}
