package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.domain.ingredient.IngredientRepository;
import com.example.cookassistant.domain.ingredient.IngredientService;
import com.example.cookassistant.domain.like.Like;
import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.web.dto.RecipeDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;

    //레시피 생성 createRecipe : 파이썬으로부터 받는 정보 (LLM을 거친 완성된 레시피)
    public RecipeDto.SaveResponseDto createRecipe(RecipeDto.SaveRequestDto requestDto , Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            Recipe recipe = Recipe.builder()
                    .name(requestDto.getName())
                    .content(requestDto.getName())
                    .imageURL(requestDto.getImageURL())
                    .createdAt(LocalDateTime.now())
                    .user(findUser)
                    .build();

            Recipe saveRecipe = recipeRepository.save(recipe);

            return RecipeDto.SaveResponseDto.builder()
                    .id(saveRecipe.getId())
                    .message("레시피 생성이 완료되었습니다")
                    .build();

        } else {
            throw new EntityNotFoundException("레시피를 생성하려는 유저를 찾지 못하였습니다");
        }

    }

    //레시피 수정 updateRecipe
    public RecipeDto.UpdateResponseDto updateRecipe(RecipeDto.UpdateRequestDto requestDto) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(requestDto.getRecipeId());

        if (optionalRecipe.isPresent()) {
            Recipe findRecipe = optionalRecipe.get();
            findRecipe.update(requestDto);

            return RecipeDto.UpdateResponseDto.builder()
                    .id(findRecipe.getId())
                    .build();
        } else {
            throw new EntityNotFoundException("해당 레시피가 존재하지 않습니다");
        }

    }

    //레시피 삭제 deleteRecipe
    public void deleteRecipe(RecipeDto.DeleteRequestDto requestDto,Long userId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(requestDto.getRecipeId());

        if (optionalRecipe.isPresent()) {
            Recipe findRecipe = optionalRecipe.get();
            User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("해당 유저가 없습니다"));

            List<Recipe> recipes = user.getRecipes();
            recipes.remove(findRecipe);




            recipeRepository.delete(findRecipe);


        } else {
            throw new EntityNotFoundException("해당 레시피를 찾지 못했습니다");
        }

    }


    //레시피 단건 조회 findRecipeById
    @Transactional(readOnly = true)
    public RecipeDto.FindResponseDto findRecipeById(Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            Recipe findRecipe = optionalRecipe.get();

            return RecipeDto.FindResponseDto.builder()
                    .recipe(findRecipe)
                    .build();
        } else {
            throw new EntityNotFoundException("해당 레시피를 찾지 못하였습니다");
        }

    }


    //레시피 전체 조회 findAll
    @Transactional(readOnly = true)
    public List<RecipeDto.FindResponseDto> findAllRecipes() {
        List<Recipe> allRecipes = recipeRepository.findAll();

        //Recipe -> FindResponseDto
        return allRecipes.stream()
                .map(RecipeDto.FindResponseDto::new)
                .toList();


    }


    //레시피 좋아요한 레시피들 조회
    @Transactional(readOnly = true)
    public List<RecipeDto.FindResponseDto> findAllLikesRecipesByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            List<Like> likes = findUser.getLikes();

            return likes.stream()
                    .map(like -> new RecipeDto.FindResponseDto(like.getRecipe()))
                    .collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException("해당 유저를 찾을 수 없습니다");
        }
    }



    //레시피 나의 레시피들 전체 조회
    @Transactional(readOnly = true)
    public List<RecipeDto.FindResponseDto> findAllRecipesByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            List<Recipe> recipes = findUser.getRecipes();

            return recipes.stream()
                    .map(RecipeDto.FindResponseDto::new)
                    .toList();


        } else {
            throw new EntityNotFoundException("해당 유저를 찾을 수 없습니다");
        }

    }


}
