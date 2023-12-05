package com.sopl.sopl.service;

import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired UserService userService;

    @Test
    public void joinUser() {
        String testEmail = "test@test.com";
        String testName = "test";
        String testPassword = "1234";

        // given
        User user1 = new User(testEmail, testName, testPassword);

        // when
        Long userId = userService.registerUser(user1);

        // then
        Assertions.assertThat(userId).isEqualTo(user1.getId());
    }
}