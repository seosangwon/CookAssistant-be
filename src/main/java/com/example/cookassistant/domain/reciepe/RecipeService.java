package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.domain.ingredient.IngredientRepository;
import com.example.cookassistant.domain.ingredient.IngredientService;
import com.example.cookassistant.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final IngredientRepository ingredientRepository;

    //레시피 생성 createRecipe


    //레시피 수정 updateRecipe


    //레시피 삭제 deleteRecipe


    //레시피 단건 조회 findRecipeById


    //레시피 전체 조회 findAll


    //레시피 좋아요한 레시피들 조회


    //레시피 나의 레시피들 전체 조회







}
