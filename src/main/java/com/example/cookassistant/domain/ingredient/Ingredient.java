package com.example.cookassistant.domain.ingredient;

import com.example.cookassistant.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String quantity;


    @Column(name = "expiration_date")
    private String expirationDate;

    @Column(name = "image_URL")
    private String imageURL;

    @Column(nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Ingredient(String name, String quantity, String expirationDate, String imageURL, String type, User user) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.imageURL = imageURL;
        this.type = type;
        this.user = user;
        setIngredient(user);
    }


    // 연관관계 편의 메서드
    public void setIngredient(User user) {
        user.getIngredients().add(this);
    }


}
