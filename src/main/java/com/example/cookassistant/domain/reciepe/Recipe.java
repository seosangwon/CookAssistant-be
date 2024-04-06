package com.example.cookassistant.domain.reciepe;

import com.example.cookassistant.domain.like.Like;
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
    private String content;

    @Column(name = "image_URL")
    private String imageURL;

    @CreatedDate
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
