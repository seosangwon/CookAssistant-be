package com.example.cookassistant.domain.like;

import com.example.cookassistant.domain.reciepe.Recipe;
import com.example.cookassistant.domain.reciepe.RecipeRepository;
import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.web.dto.LikeDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;


    //좋아요 생성 1.현재 한번 생성된 좋아요가 계속 생성 됨
    public LikeDto.LikeSaveResponseDto createLike(LikeDto.LikeSaveRequestDto requestDto) {
        User findUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾지 못했습니다."));
        Recipe findRecipe = recipeRepository.findById(requestDto.getRecipeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 레시피를 찾지 못했습니다."));

        // 중복 좋아요 검사
        Optional<Like> existingLike = likeRepository.findByUserIdAndRecipeId(findUser.getId(), findRecipe.getId());
        if (existingLike.isPresent()) {
            throw new IllegalStateException("이미 좋아요가 등록되어 있습니다.");
        }

        // 중복이 없으면 새로운 좋아요 생성
        Like like = Like.builder()
                .user(findUser)
                .recipe(findRecipe)
                .build();

        likeRepository.save(like);

        return LikeDto.LikeSaveResponseDto.builder()
                .like(like)
                .build();
    }


    //좋아요 삭제 1. 좋아요가 삭제되면 레시피랑 유저도 삭제됨(CASCADE 때문) , 레시피를 삭제하면 좋아요도 삭제되는거는 맞음
    public void deleteLike(LikeDto.LikeDeleteRequestDto requestDto) {
        Optional<Like> optionalLike = likeRepository.findByUserIdAndRecipeId(requestDto.getUserId(), requestDto.getRecipeId());
        if (optionalLike.isPresent()) {
            Like findLike = optionalLike.get();

            likeRepository.delete(findLike);


        } else {
            throw new EntityNotFoundException("해당 좋아요를 찾을 수 없습니다");
        }


    }




}
