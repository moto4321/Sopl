package com.sopl.sopl.controller;

import com.sopl.sopl.service.dto.UserInfoDto;
import com.sopl.sopl.service.oauth.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final KakaoService kakaoService;

    @RequestMapping("/kakaoLogin")
    public String kakaoLoginPage() {
//        log.info("session:: " + session);
        return "oauth/kakaoLogin";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/api/{type}/oauth/redirect") // type: google, kakao ...
    public String oauthLogin(HttpServletRequest request,
                           HttpServletResponse response,
                           @PathVariable(value = "type") String type,
                           @RequestParam(value = "code") String code) {
        System.out.println("authCode:: " + code);
        System.out.println("type:: " + type);

        // code로 사용자 정보 요청 로직
        String access_Token = kakaoService.getAccessToken(code);
        System.out.println("###access_token#### : " + access_Token);
        UserInfoDto userInfo = kakaoService.getUserinfo(request, response, access_Token, type);

        return "redirect:/";
    }
}
