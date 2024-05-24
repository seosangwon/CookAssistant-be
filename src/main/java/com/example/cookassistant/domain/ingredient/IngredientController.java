package com.example.cookassistant.domain.ingredient;

import com.example.cookassistant.base.RsData;
import com.example.cookassistant.domain.user.MemberContext;
import com.example.cookassistant.util.Util;
import com.example.cookassistant.web.dto.IngredientDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Tag(name = "Ingredient API", description = "식재료 관련 API 입니다.")
@RestController
@Slf4j
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @Operation(summary = "식재료 작성 api 입니다.")
    @PostMapping("/new")
    public ResponseEntity<RsData> createIngredient(@AuthenticationPrincipal MemberContext memberContext, @RequestBody @Valid IngredientDto.SaveRequestDto requestDto,
                                                   BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "식재료 생성 유효성 검증 실패", bindingResult.getAllErrors()));
        }
        IngredientDto.SaveResponseDto saveResponseDto = ingredientService.createIngredient(requestDto, memberContext.getId());

        return Util.spring.responseEntityOf(RsData.successOf(saveResponseDto));
    }

    @Operation(summary = "식재료 단건 조회 레시피 입니다")
    @GetMapping("/{ingredientId}")
    public ResponseEntity<RsData> findIngredient(@PathVariable("ingredientId") Long ingredientId) {
        IngredientDto.FindResponseDto findResponseDto = ingredientService.findIngredientById(ingredientId);
        return Util.spring.responseEntityOf(RsData.successOf(findResponseDto));
    }

    @Operation(summary = "유저의 전체 식재료 조회 레시피 입니다")
    @GetMapping("/all")
    public ResponseEntity<RsData> findAllIngredients(@AuthenticationPrincipal MemberContext memberContext) {
        List<IngredientDto.FindResponseDto> findAllResponseDto = ingredientService.findAllIngredient(memberContext.getId());

        return Util.spring.responseEntityOf(RsData.successOf(findAllResponseDto));
    }

    @Operation(summary = "식재료 수정 레시피 입니다")
    @PutMapping ("/update")
    public ResponseEntity<RsData> updateIngredient(@RequestBody @Valid IngredientDto.UpdateRequestDto requestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "식재료 수정 유효성 검증 실패", bindingResult.getAllErrors()));
        }

        IngredientDto.UpdateResponseDto updateResponseDto = ingredientService.updateIngredient(requestDto);

        return Util.spring.responseEntityOf(RsData.successOf(updateResponseDto));
    }


    @Operation(summary = "식재료 삭제 레시피 입니다")
    @DeleteMapping("/delete")
    public ResponseEntity<RsData> deleteIngredient(@AuthenticationPrincipal MemberContext memberContext, @RequestBody @Valid IngredientDto.DeleteRequestDto requestDto,
                                                   BindingResult bindingResult) {

        IngredientDto.FindResponseDto responseDto = ingredientService.findIngredientById(requestDto.getIngredientId());
        if (responseDto==null) {
            Util.spring.responseEntityOf(RsData.of("F-1", "해당 식재료를 찾지 못하였습니다"));
        }

        if (bindingResult.hasErrors()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "식재료 삭제 유효성 검증 실패", bindingResult.getAllErrors()));
        }

        ingredientService.ingredientDelete(requestDto, memberContext.getId());

        return Util.spring.responseEntityOf(RsData.of("S-1","식재료 삭제에 성공하였습니다"));

    }


}
