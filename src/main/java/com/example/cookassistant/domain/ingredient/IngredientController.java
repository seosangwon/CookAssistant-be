package com.example.cookassistant.domain.ingredient;

import com.example.cookassistant.web.dto.IngredientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Tag(name="Ingredient API" , description = "식재료 관련 API 입니다.")
@RestController
@Slf4j
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @Operation(summary = "식재료 작성 api 입니다.")
    @PostMapping("/new")
    public ResponseEntity<?> createIngredient(@RequestBody @Valid IngredientDto.SaveRequestDto requestDto, BindingResult bindingResult) {
        IngredientDto.SaveResponseDto saveResponseDto = ingredientService.createIngredient(requestDto);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(saveResponseDto);
    }

    @Operation(summary = "식재료 단건 조회 레시피 입니다")
    @GetMapping("/{ingredientId}")
    public ResponseEntity<?> findIngredient(@PathVariable("ingredientId") Long ingredientId) {
        IngredientDto.FindResponseDto findResponseDto = ingredientService.findIngredientById(ingredientId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(findResponseDto);
    }

    @Operation(summary = "전체 식재료 조회 레시피 입니다")
    @GetMapping("/all/{userId}")
    public ResponseEntity<?> findAllIngredients(@PathVariable("userId") Long userId) {
        List<IngredientDto.FindResponseDto> findAllResponseDto = ingredientService.findAllIngredient(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(findAllResponseDto);
    }

    @Operation(summary = "식재료 수정 레시피 입니다")
    @PutMapping("/update")
    public ResponseEntity<?> updateIngredient(@RequestBody @Valid IngredientDto.UpdateRequestDto requestDto, BindingResult bindingResult) {
        IngredientDto.UpdateResponseDto updateResponseDto = ingredientService.updateIngredient(requestDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updateResponseDto);
    }


    @Operation(summary = "식재료 삭제 레시피 입니다")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteIngredient(@RequestBody @Valid IngredientDto.DeleteRequestDto requestDto , BindingResult bindingResult) {
        ingredientService.ingredientDelete(requestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }







}
