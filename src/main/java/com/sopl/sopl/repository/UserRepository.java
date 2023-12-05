package com.sopl.sopl.repository;

import com.sopl.sopl.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    /**
     * 회원가입
     */
    public Long save(User user) {
        em.persist(user);
        return user.getId();
    }

    /**
     * 유저 조회
     */
    public Optional<User> findUserById(Long id) {
        User findUser = em.find(User.class, id);
        return Optional.ofNullable(findUser);
    }

    /**
     * email 조회
     */
    public Optional<User> findUserByEmail(String email) {
        System.out.println("haha:: " + email);
//        User findUser = em.find(User.class, email);
        List<User> findUserList = em.createQuery(
                "select u from User u where u.email = :email", User.class
        ).setParameter("email", email).getResultList();
//        return Optional.ofNullable(findUser);
        return findUserList.stream().findAny();
    }



}
