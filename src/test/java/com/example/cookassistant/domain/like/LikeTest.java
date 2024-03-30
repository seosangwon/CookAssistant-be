package com.example.cookassistant.domain.like;

import com.example.cookassistant.domain.reciepe.Recipe;
import com.example.cookassistant.domain.reciepe.RecipeRepository;
import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.domain.user.UserRepository;
import com.example.cookassistant.domain.user.UserRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@SpringBootTest
public class LikeTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    RecipeRepository recipeRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void testLike() {

        //given
        User user = User.builder()
                .email("zmdk1205@naver.com")
                .nickName("sangwon")
                .password("1234")
                .role(UserRole.USER)
                .build();

        Recipe recipe = Recipe.builder()
                .content("content")
                .createdAt(LocalDateTime.now())
                .name("name")
                .build();

        Like like = Like.builder()
                .user(user)
                .recipe(recipe)
                .build();

        //when
        User savedUser = userRepository.save(user); // 저장된 엔티티 반환
        Recipe savedRecipe = recipeRepository.save(recipe);
        Like savedLike = likeRepository.save(like);


        //then
        Assertions.assertThat(savedUser.getLikes().get(0)).isEqualTo(savedLike);
        Assertions.assertThat(savedRecipe.getLikes().get(0)).isEqualTo(savedLike);



    }

}
