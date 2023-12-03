package com.sopl.sopl.service;

import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long registerUser(User user) {
        validateDuplicatedEmail(user);

        return userRepository.save(user);
    }

    private void validateDuplicatedEmail(User user) {
        String userEmail = user.getEmail();
        Optional<User> findUser = userRepository.findUserByEmail(userEmail);
        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 존재하는 Email입니다.");
        }
    }
}
