package com.example.cookassistant.web.dto;

import com.example.cookassistant.domain.like.Like;
import lombok.Builder;
import lombok.Data;

public class LikeDto {


    @Data
    public static class LikeSaveRequestDto {
        private Long userId;
        private Long recipeId;
    }

    @Data
    public static class LikeSaveResponseDto {

        private Long userId;
        private Long recipeId;

        @Builder
        public LikeSaveResponseDto(Like like) {
            this.userId = like.getUser().getId();
            this.recipeId = like.getRecipe().getId();
        }




    }


    @Data
    public static class LikeDeleteRequestDto {
        private Long userId;
        private Long recipeId;
    }



}
