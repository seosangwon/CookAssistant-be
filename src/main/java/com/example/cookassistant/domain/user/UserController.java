package com.example.cookassistant.domain.user;

import com.example.cookassistant.base.RsData;
import com.example.cookassistant.util.Util;
import com.example.cookassistant.web.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
@Tag(name = "USER API ", description = "USER 관련 API 입니다")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Operation(summary = "백엔드 전용 자체 테스트입니다 ")
    @GetMapping("/test")
    public ResponseEntity<RsData> me(@AuthenticationPrincipal MemberContext memberContext) {

        if (memberContext == null) {
            return Util.spring.responseEntityOf(RsData.failOf(null));
        }
        return Util.spring.responseEntityOf(RsData.successOf(memberContext));
    }


    //유저 생성
    @Operation(summary = "유저 생성 api 입니다")
    @PostMapping("/new")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto.SaveRequestDto requestDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "회원 정보를 올바르게 입력해주세요", bindingResult.getAllErrors()));
        }

        UserDto.SaveResponseDto responseDto = userService.createUser(requestDto);

        return Util.spring.responseEntityOf(RsData.of("S-1", "정상적으로 회원가입이 되었습니다", responseDto));
    }

    @Operation(summary = "유저 로그인 api 입니다")
    @PostMapping("/login")
    public ResponseEntity<RsData> login(@RequestBody @Valid UserDto.LoginRequestDto requestDto, BindingResult bindingResult) {

        User user = userService.findByEmail(requestDto.getEmail()).orElse(null);
        log.info("Login attempt with email: {} and password: {}", requestDto.getEmail(), requestDto.getPassword());

        try {
            if (bindingResult.hasErrors()) {
                return Util.spring.responseEntityOf(RsData.of("F-3", "로그인 유효성 검증 실패 ", bindingResult.getAllErrors()));
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("로그인시 eamil과 password 모두 입력해주세요");
        }


        if (user == null) {
            return Util.spring.responseEntityOf(RsData.of("F-1", "해당 유저를 찾지 못하였습니다"));
        }


        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            return Util.spring.responseEntityOf(RsData.of("F-2", "회원정보가 일치하지 않습니다"));
        }


        log.debug("Util.json.toStr(user.getAccessTokenClaims()) : " + Util.json.toStr(user.getAccessTokenClaims()));
        String accessToken = userService.getAccessToken(user);


        return Util.spring.responseEntityOf(RsData.of("S-1", "로그인 성공 , AccessToken을 발급합니다", Util.mapOf("accessToken", accessToken)),
                Util.spring.httpHeadersOf("Authentication", accessToken)
        );


    }

    @Operation(summary = "자신의 myPage 조회 API 입니다")
    @GetMapping("/myPage")
    public ResponseEntity<RsData> getMyPage(@AuthenticationPrincipal MemberContext memberContext) {
        UserDto.UserDetailsDto responseDto = new UserDto.UserDetailsDto();
        responseDto.setUserId(memberContext.getId());
        responseDto.setEmail(memberContext.getEmail());


        return Util.spring.responseEntityOf(RsData.successOf(responseDto));


    }


}
