package com.example.cookassistant.domain.ingredient;

import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.domain.user.UserRole;
import com.example.cookassistant.web.dto.IngredientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class IngredientServiceTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IngredientRepository ingredientRepository;


    @Test
    void createIngredient() {



    }

    @Test
    void findByIngredientById() {

        //given

        //when

        //then
    }

    @Test
    void findAllIngredient() {
    }

    @Test
    void updateIngredient() {
    }

    @Test
    void ingredientDelete() {
    }





}