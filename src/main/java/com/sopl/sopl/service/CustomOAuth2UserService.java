package com.sopl.sopl.service;

import com.sopl.sopl.domain.User;
import com.sopl.sopl.repository.UserRepository;
import com.sopl.sopl.service.dto.SessionUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;
//    private final CustomAuthorityUtils authorityUtils;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);  // OAuth2 정보를 가져옵니다.

        log.info("oAuth2User.getName():: ", oAuth2User.getName());

        Map<String, Object> originAttributes = oAuth2User.getAttributes();  // OAuth2User의 attribute

        // OAuth2 서비스 id (google, kakao, naver)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();    // 소셜 정보를 가져옵니다.

        // OAuthAttributes: OAuth2User의 attribute를 서비스 유형에 맞게 담아줄 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, originAttributes);
        User user = saveOrUpdate(attributes);
        String email = user.getEmail();
//        List<GrantedAuthority> authorities = authorityUtils.createAuthorities(email);

//        return new OAuth2CustomUser(registrationId, originAttributes, authorities, email);
        return new OAuth2CustomUser(email);



//        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
//
//        // 현재 진행중인 서비스를 구분하기 위해 문자열로 받음. oAuth2UserRequest.getClientRegistration().getRegistrationId()에 값이 들어있다. {registrationId='naver'} 이런식으로
//        String registrationId = userRequest.getClientRegistration().getRegistrationId();
//
//        // OAuth2 로그인 시 키 값이 된다. 구글은 키 값이 "sub"이고, 네이버는 "response"이고, 카카오는 "id"이다. 각각 다르므로 이렇게 따로 변수로 받아서 넣어줘야함.
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        // OAuth2 로그인을 통해 가져온 OAuth2User의 attribute를 담아주는 of 메소드.
//        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        User user = saveOrUpdate(attributes);
//        httpSession.setAttribute("user", new SessionUser(user.getEmail(), user.getName()));
//
//        System.out.println(attributes.getAttributes());
//        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//                , attributes.getAttributes()
//                , attributes.getNameAttributeKey());
    }


    /**
     * 이미 존재하는 회원이라면 이름과 프로필이미지를 업데이트해줍니다. 이미지 업데이트 할 필요는 없을듯
     * 처음 가입하는 회원이라면 User 테이블을 생성합니다.
     **/
    private User saveOrUpdate(OAuthAttributes authAttributes) {
//        User user = userRepository.findByEmail(authAttributes.getEmail())
//                .map(entity -> entity.update(authAttributes.getName(), authAttributes.getProfileImageUrl()))
//                .orElse(authAttributes.toEntity());
//
//        return userRepository.save(user);

        User user = userRepository.findByEmail(authAttributes.getEmail())
//                .map(entity -> entity.update(authAttributes.getName())
                .orElse(authAttributes.toEntity());

        return userRepository.save(user);
    }
}
