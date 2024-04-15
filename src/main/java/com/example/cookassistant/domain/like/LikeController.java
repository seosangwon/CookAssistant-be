package com.example.cookassistant.domain.like;

import com.example.cookassistant.web.dto.LikeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Like API", description = "좋아요 API 입니다")
@RestController
@Slf4j
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    //좋아요 생성 createLike
    @Operation(summary = "좋아요 생성 api 입니다")
    @PostMapping("/new")
    public ResponseEntity<?> createLike(@RequestBody @Valid LikeDto.LikeSaveRequestDto requestDto, BindingResult bindingResult) {
        LikeDto.LikeSaveResponseDto responseDto = likeService.createLike(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);


    }

    //좋아요 삭제 deleteLie
    @Operation(summary = "좋아요 삭제 api 입니다")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLike(@RequestBody @Valid LikeDto.LikeDeleteRequestDto requestDto, BindingResult bindingResult) {
        likeService.deleteLike(requestDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
