package com.example.cookassistant.domain.user;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @Transactional
    @Rollback(value = false)
    public void testUser() {

        //given
        User user = User.builder()
                .email("zmdk1205@naver.com")
                .nickName("sangwon")
                .password("1234")
                .role(UserRole.USER)
                .build();

        //when
        User savedUser = userRepository.save(user); // 저장된 엔티티 반환
        Long savedId = savedUser.getId();
        Optional<User> findUser = userRepository.findById(savedId);

        //then
        Assertions.assertThat(findUser.get().getNickName()).isEqualTo(user.getNickName());



    }
}
