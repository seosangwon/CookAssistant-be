package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.domain.like.Like;
import com.example.cookassistant.domain.user.User;
import com.example.cookassistant.web.dto.RecipeDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    @Lob
    private String content;

    @Column(name = "image_URL")
    private String imageURL;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.REMOVE)
    private List<Like> likes = new ArrayList<>();

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


    @Builder
    public Recipe(String name, String content, String imageURL, LocalDateTime createdAt , User user) {
        this.name = name;
        this.content = content;
        this.imageURL = imageURL;
        this.createdAt = createdAt;
        this.user=user;
        setUser(user);

    }

    //연관관계 편의 메서드
    public void setUser(User user) {
        user.getRecipes().add(this);

    }


    public void update(RecipeDto.UpdateRequestDto requestDto) {
        this.name=requestDto.getName();
        this.content= requestDto.getContent();
        this.imageURL= requestDto.getImageURL();
        this.createdAt=requestDto.getCreatedAt();
    }
}
