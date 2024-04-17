package com.example.cookassistant.domain.user;

import com.example.cookassistant.web.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
@Tag(name = "USER API ", description = "USER 관련 API 입니다")
public class UserController {

    private final UserService userService;

    //유저 생성
    @Operation(summary = "유저 생성 임시 api 입니다")
    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto.SaveRequestDto requestDto, BindingResult bindingResult) {
        UserDto.SaveResponseDto responseDto = userService.createUser(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseDto);
    }

}
