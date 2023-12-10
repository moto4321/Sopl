package com.sopl.sopl.service;

import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        validateDuplicatedEmail(user);

        return userRepository.save(user);
    }

    private void validateDuplicatedEmail(User user) {
        String userEmail = user.getEmail();
        System.out.println("userEmail:: " + userEmail);
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        System.out.println("findUser:: " + findUser);
        if (findUser.isPresent()) {
            throw new IllegalStateException("이미 존재하는 Email입니다.");
        }
    }
}
