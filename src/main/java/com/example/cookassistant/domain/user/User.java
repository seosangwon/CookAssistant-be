package com.example.cookassistant.domain.user;

import com.example.cookassistant.domain.ingredient.Ingredient;
import com.example.cookassistant.domain.like.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;


    @Column(nullable = false)
    private String nickName;

    @Comment("이메일 형식")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoll role;

    @OneToMany(mappedBy = "user")
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();


    @Builder
    public User(String nickName, String email, String password, UserRoll role) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.role = role;


    }

    /**
     * 비즈니스 로직
     * 1.비밀번호 변경 2. 닉네임 변경
     */

    public User updateName(String nickName) {
        this.nickName = nickName;
        return this;
    }


}
