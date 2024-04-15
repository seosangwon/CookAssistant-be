package com.example.cookassistant.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like , Long> {

    //userId,recipeId로 이미 생성된 좋아요가 있는지 확인
    Optional<Like> findByUserIdAndRecipeId(Long userId, Long recipeId);
}
