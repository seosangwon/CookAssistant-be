package com.example.cookassistant.domain.ingredient;

import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.web.dto.IngredientDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional

public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;


    //생성 createIngredient
    public IngredientDto.SaveResponseDto createIngredient(IngredientDto.SaveRequestDto requestDto) {

        Optional<User> optionalUser = userRepository.findById(requestDto.getUserId());
        if (optionalUser.isPresent()) {
            User findUser = optionalUser.get();
            Ingredient newIngredient = Ingredient.builder()
                    .name(requestDto.getName())
                    .expirationDate(requestDto.getExpirationDate())
                    .imageURL(requestDto.getImageURL())
                    .quantity(requestDto.getQuantity())
                    .type(requestDto.getType())
                    .user(findUser)
                    .build();

            Ingredient saveIngredient = ingredientRepository.save(newIngredient);

            return IngredientDto.SaveResponseDto.builder()
                    .id(saveIngredient.getId())
                    .message("식재료 저장이 완료되었습니다")
                    .build();

        } else {
            throw new EntityNotFoundException("해당 유저를 찾지 못하였습니다");
        }


    }


    //상세 페이지
    // 단건 조회 : findIngredientById
    @Transactional(readOnly = true)
    public IngredientDto.FindResponseDto findIngredientById(Long id) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);

        if (optionalIngredient.isPresent()) {
            Ingredient findIngredient = optionalIngredient.get();

            return IngredientDto.FindResponseDto.builder()
                    .ingredient(findIngredient)
                    .build();

        } else {
            throw new EntityNotFoundException("해당 식재료를 찾지 못하였습니다");
        }
    }


    //모든 재료 조회 : findAllIngredient
    @Transactional(readOnly = true)
    public List<IngredientDto.FindResponseDto> findAllIngredient(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Ingredient> ingredients = user.getIngredients();

            return ingredients.stream()
                    .map(IngredientDto.FindResponseDto::new)
                    .toList();

        } else {
            throw new EntityNotFoundException("해당 유저를 찾지 못하였습니다");
        }
    }


    //업데이트 updateIngredient
    @Transactional
    public IngredientDto.UpdateResponseDto updateIngredient(IngredientDto.UpdateRequestDto requestDto) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(requestDto.getId());

        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get(); // 유저의 정보가 있음
            ingredient.update(requestDto);

            return IngredientDto.UpdateResponseDto.builder()
                    .id(ingredient.getId())
                    .message("식재료 수정이 완료되었습니다")
                    .build();

        } else {
            throw new EntityNotFoundException("해당 식재료를 찾지 못하였습니다");
        }
    }



    //삭제 deleteIngredient
    @Transactional
    public void ingredientDelete(IngredientDto.DeleteRequestDto requestDto) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(requestDto.getIngredientId());


        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            User user = userRepository.findById(requestDto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
            List<Ingredient> ingredients = user.getIngredients();
            ingredients.remove(ingredient);


            ingredientRepository.deleteById(requestDto.getIngredientId());


        } else {
            throw new EntityNotFoundException("해당 재료를 찾을 수 없습니다");
        }

    }


}
