package com.example.cookassistant.domain.user;

import com.example.cookassistant.domain.ingredient.Ingredient;
import com.example.cookassistant.domain.like.Like;
import com.example.cookassistant.domain.reciepe.Recipe;
import com.example.cookassistant.util.Util;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
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

    @Column(columnDefinition = "TEXT")
    private String accessToken;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Ingredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();


    @Builder
    public User(Long id,String nickName, String email, String password, UserRole role,String accessToken) {
        this.id= id;
        this.nickName = nickName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.accessToken = accessToken;


    }

    public static User fromMap(Map<String, Object> userMap) {
        return fromJwtClaims(userMap);
    }

    public  Map<String, Object> toMap() {
        return Util.mapOf(
                "id", getId(),
                "username", getNickName(),
                "email", getEmail(),
                "accessToken", getAccessToken(),
                "authorities", getAuthorities()
        );
    }


    private static User fromJwtClaims(Map<String, Object> jwtClaims) {
        long id = 0;

        if (jwtClaims.get("id") instanceof Long) {

            id = (long) jwtClaims.get("id");

        } else if (jwtClaims.get("id") instanceof Integer) {
            id = (long) (int) jwtClaims.get("id");

        }

        String username = (String) jwtClaims.get("username");
        String email = (String) jwtClaims.get("email");
        String accessToken = (String) jwtClaims.get("accessToken");

        return User.builder()
                .id(id)
                .nickName(username)
                .email(email)
                .accessToken(accessToken)
                .build();


    }

    /**
     * 비즈니스 로직
     * 1.비밀번호 변경 2. 닉네임 변경
     */

    public User updateName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    //회원이 가지고 있는 권한을 List<GrantAuthority> 형태로 return
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("USER")); // String이여야함

        return authorities;

    }

    //토큰에 담아낼 user 정보들 추출
    public Map<String, Object> getAccessTokenClaims() {
        return Util.mapOf(
                "id", getId(),
                "username", getNickName(),
                "email", getEmail(),
                "accessToken", getAccessToken(),
                "authorities", getAuthorities()
        );
    }


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


}
