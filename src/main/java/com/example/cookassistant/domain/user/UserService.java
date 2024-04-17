package com.example.cookassistant.domain.user;

import com.example.cookassistant.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //유저 생성 createUser
    public UserDto.SaveResponseDto createUser(UserDto.SaveRequestDto requestDto) {
        Optional<User> findUser = userRepository.findByEmail(requestDto.getEmail());

        if (findUser.isPresent()) {
            throw new IllegalArgumentException("해당 유저가 이미 존재합니다");
        } else {
            User user = User.builder()
                    .email(requestDto.getEmail())
                    .nickName(requestDto.getNickName())
                    .password(requestDto.getPassword())
                    .role(requestDto.getRole())
                    .build();

            User saveUser = userRepository.save(user);

            return UserDto.SaveResponseDto.builder()
                    .id(saveUser.getId())
                    .message("회원가입 완료")
                    .build();

        }


    }




}
