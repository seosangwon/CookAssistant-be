package com.example.cookassistant.domain.user;

import com.example.cookassistant.web.dto.UserDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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

            return UserDto.SaveResponseDto.builder()
                    .id(saveUser.getId())
                    .message("회원가입 완료")
                    .build();

        }


    }

    //유저 로그인
//    public UserDto.LoginResponseDto loginUser(UserDto.LoginRequestDto requestDto) {
//        Optional<User> optionalUser = userRepository.findByEmail(requestDto.getEmail());
//        if (optionalUser.isPresent()) {
//            User findUser = optionalUser.get();
//            if (!passwordEncoder.matches(requestDto.getPassword(), findUser.getPassword())) {
//                throw new IllegalArgumentException("잘못된 비밀번호 입니다 ");
//            }
//        } else {
//            throw new EntityNotFoundException("해당 이메일로 가입한 유저가 없습니다 ");
//        }
//
//        return
//
//    }
//



}
