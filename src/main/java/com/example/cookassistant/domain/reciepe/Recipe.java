package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.domain.like.Like;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {
    @Id
    @GeneratedValue
    @Column(name = "recipe_id")
    private Long id;

    @Column(nullable = false)
    private String name;


    @Column(nullable = false)
    private String content;

    @Column(name = "image_URL")
    private String imageURL;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipe")
    private List<Like> likes = new ArrayList<>();


    @Builder
    public Recipe(String name, String content, String imageURL, LocalDateTime createdAt) {
        this.name = name;
        this.content = content;
        this.imageURL = imageURL;
        this.createdAt = createdAt;
    }


}
