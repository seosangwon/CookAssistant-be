package com.example.cookassistant.domain.like;

import com.example.cookassistant.domain.reciepe.Recipe;
import com.example.cookassistant.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    @Builder
    public Like(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;

        setUser(user);
        setRecipe(recipe);
    }


    //좋아요가 생성되면 유저의 좋아요 리스트에 추가 , 레시피의 좋아요 리스트에 추가
    public void setUser(User user) {
        user.getLikes().add(this);
    }

    public void setRecipe(Recipe recipe) {
        recipe.getLikes().add(this);
    }


}
