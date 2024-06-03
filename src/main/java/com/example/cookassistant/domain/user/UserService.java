package com.example.cookassistant.domain.user;

import com.example.cookassistant.config.AppConfig;
import com.example.cookassistant.security.jwt.JwtProvider;
import com.example.cookassistant.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    //유저 생성 createUser
    public UserDto.SaveResponseDto createUser(UserDto.SaveRequestDto requestDto) {
        Optional<User> findUser = userRepository.findByEmail(requestDto.getEmail());

        if (findUser.isPresent()) {
            throw new IllegalArgumentException("해당 유저가 이미 존재합니다");
        } else {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .nickName(requestDto.getNickName())
                    .password(passwordEncoder.encode(requestDto.getPassword()))
                    .role(requestDto.getRole())
                    .build();

            User saveUser = userRepository.save(user);
            UserDto.SaveResponseDto responseDto = new UserDto.SaveResponseDto();
            responseDto.setId(saveUser.getId());

            return responseDto;

        }


    }


    //유저 email로 찾아오기
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);

    }

    @Transactional
    public String getAccessToken(User user) {
        String accessToken = user.getAccessToken();
        if (StringUtils.hasLength(accessToken) == false) {
            accessToken = jwtProvider.generateAccessToken(user.getAccessTokenClaims(), 60 * 60);

            user.setAccessToken(accessToken);

        }

        return accessToken;
    }

    public boolean verifyWithWhiteList(User user, String token) {
        log.info("verifyWithWhiteList 메서드 실행  : 유저 이메일:"+user.getEmail());
        log.info("verifyWithWhiteList 메서드 실행  : 유저 토큰:"+user.getAccessToken());
        log.info("verifyWithWhiteList 메서드 실행  : token:"+token);
        log.info("equals결과 : "+user.getAccessToken().equals(token));
        return user.getAccessToken().equals(token);
    }



    @Cacheable("user")
    public Map<String, Object> getUserMapByUserEmail__cached(String email) {
        User user = userRepository.findByEmail(email).orElse(null);

        return user.toMap();
    }

    public User getByUserEmail_cached(String email) {
        UserService thisObj = (UserService) AppConfig.getContext().getBean("userService");
        Map<String, Object> userMap = thisObj.getUserMapByUserEmail__cached(email);

        return User.fromMap(userMap);
    }


}
