package com.sopl.sopl.service;

//import com.sopl.sopl.domain.AuthToken;
import com.sopl.sopl.domain.TokenResponse;
//import com.sopl.sopl.repository.RedisTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

//    private final RedisTokenRepository redisTokenRepository;

    // 토큰 저장
//    @Transactional
//    public void saveToken(Long userId, TokenResponse token) {
//        try {
//            redisTokenRepository.save(AuthToken.builder()
//                    .userId(userId)
//                    .token_JWT(token.getAccessToken())
//                    .expiration(token.getExpiresIn())
//                    .build());
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

    // 토큰 조회
//    @Transactional(readOnly = true)
//    public String getToken(Long userId) {
////        try {
//            return redisTokenRepository.findById(userId)
//                    .orElseThrow(IllegalArgumentException::new) // 토큰이 존재하지 않은 경우
//                    .getToken_JWT();
////        } catch (Exception e) {
////            log.error(e.getMessage());
////        }
//    }

    // 토큰 삭제
//    @Transactional
//    public void deleteToken(AuthToken token) {
//        try {
//            redisTokenRepository.delete(token);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

}
