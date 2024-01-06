package com.sopl.sopl.service;

import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import com.sopl.sopl.service.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Slf4j
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

    public void loginUser(
            HttpServletRequest request,
            HttpServletResponse response,
            UserInfoDto userInfo,
            String access_token
    ) {
        HttpSession session = request.getSession();
//        session.setAttribute(SessionConstants.LOGIN_MEMBER, user);
//        session.setAttribute(SessionConstants.ACCESS_TOKEN, access_token);
        session.setAttribute("userInfo", userInfo);
        session.setAttribute("accessToken", access_token);
        log.info("testSession:: " + session.getId());
    }
}
