package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.base.RsData;
import com.example.cookassistant.domain.user.MemberContext;
import com.example.cookassistant.util.Util;
import com.example.cookassistant.web.dto.RecipeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@Tag(name = "Recipe API", description = "레시피 관련 api 입니다")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    //레시피 생성 createRecipe
    @Operation(summary = "레시피 생성 api 입니다")
    @PostMapping("/new")
    public ResponseEntity<RsData> createRecipe(@AuthenticationPrincipal MemberContext memberContext, @RequestBody @Valid RecipeDto.SaveRequestDto requestDto,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "레시피 생성 유효성 검증 실패", bindingResult.getAllErrors()));
        }

        RecipeDto.SaveResponseDto saveResponseDto = recipeService.createRecipe(requestDto , memberContext.getId());
        return Util.spring.responseEntityOf(RsData.successOf(saveResponseDto));



    }

    //레시피 단건 조회 findRecipeById
    @Operation(summary = "레시피 단건 조회 api 입니다")
    @GetMapping("/{recipeId}")
    public ResponseEntity<?> findRecipeById(@PathVariable("recipeId") Long recipeId) {
        RecipeDto.FindResponseDto findResponseDto = recipeService.findRecipeById(recipeId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(findResponseDto);
    }


    //레시피 전체 조회 findAllRecipe
    @Operation(summary = "레시피 전체 조회 api 입니다")
    @GetMapping("/all")
    public ResponseEntity<?> findAllRecipe() {
        List<RecipeDto.FindResponseDto> allRecipes = recipeService.findAllRecipes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(allRecipes);
    }

    //유저의 레시피 전체 조회 findAllRecipeByUserId
    @Operation(summary = "유저의 레시피 전체 조회 api 입니다")
    @GetMapping("/all/{userId}")
    public ResponseEntity<?> findAllRecipeByUserId(@PathVariable("userId") Long userId) {
        List<RecipeDto.FindResponseDto> allRecipes = recipeService.findAllRecipesByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(allRecipes);
    }

    //좋아요한 레시피 전체 조회 findAllLikeRecipeByUserId
    @Operation(summary = "좋아요한 레시피 전체 조회 api 입니다")
    @GetMapping("/likes/{userId}")
    public ResponseEntity<?> findAllLikeRecipeByUserId(@PathVariable("userId") Long userId) {
        List<RecipeDto.FindResponseDto> likeRecipes = recipeService.findAllLikesRecipesByUserId(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(likeRecipes);
    }

    //레시피 수정 updateRecipe
    @Operation(summary = "레시피 수정 api  입니다")
    @PutMapping("/update")
    public ResponseEntity<?> updateRecipe(@RequestBody @Valid RecipeDto.UpdateRequestDto requestDto, BindingResult bindingResult) {
        RecipeDto.UpdateResponseDto updateResponseDto = recipeService.updateRecipe(requestDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(updateResponseDto);
    }

    //레시피 삭제 deleteRecipe
    @Operation(summary = "레시피 삭제 api 입니다")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRecipe(@RequestBody @Valid RecipeDto.DeleteRequestDto requestDto, BindingResult bindingResult) {
        recipeService.deleteRecipe(requestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
