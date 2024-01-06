package com.sopl.sopl.apis;

import com.sopl.sopl.service.UserService;
import com.sopl.sopl.service.dto.UserInfoDto;
import com.sopl.sopl.service.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final KakaoService kakaoService;

    @Value("${oauth.kakao.client.id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.redirectUrl}")
    private String kakaoRedirectUrl;

    @PostMapping("/v1/oauth/kakao")
    public String getKakaoLoginPage(){
        String link = "https://kauth.kakao.com/oauth/authorize?client_id=" +
                kakaoClientId +
                "&redirect_uri=" +
                kakaoRedirectUrl +
                "&response_type=code";
        System.out.println(link);
        return link;
    }

//    @GetMapping("/{type}/oauth/redirect") // type: google, kakao ...
//    public String oauthLogin(HttpServletRequest request,
//                           HttpServletResponse response,
//                           @PathVariable(value = "type") String type,
//                           @RequestParam(value = "code") String code) {
//        System.out.println("authCode:: " + code);
//        System.out.println("type:: " + type);
//
//        // code로 사용자 정보 요청 로직
//        String access_Token = kakaoService.getAccessToken(code);
//        System.out.println("###access_token#### : " + access_Token);
//        kakaoService.getUserinfo(request, response, access_Token, type);
//        return "redirect:/home";
//    }

    @PostMapping("/logout")
    public void logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @GetMapping("/sessionTest")
    public void sessionTest(
            @SessionAttribute("userInfo") UserInfoDto userInfo
    ) {
        log.info(userInfo.getEmail());
    }
}
