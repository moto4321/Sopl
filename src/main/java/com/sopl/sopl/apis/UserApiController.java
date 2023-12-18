package com.sopl.sopl.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

//    @Value("${oauth.google.client.id}")
//    private String googleClientId;
//    @Value("${oauth.google.client.pw}")
//    private String googleClientPw;
//    @Value("${oauth.google.redirectUrl}")
//    private String googleRedirectUrl;

    @Value("${oauth.kakao.client.id}")
    private String kakaoClientId;
    @Value("${oauth.kakao.redirectUrl}")
    private String kakaoRedirectUrl;


    @GetMapping("/ping")
    public Boolean test() {
        return true;
    }

//    @PostMapping("/v1/oauth/google")
//    public String getGoogleLoginPage(){
//        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=" +
//                googleClientId +
//                "&redirect_uri=" +
//                googleRedirectUrl +
//                "&response_type=code&scope=email%20profile%20openid&access_type=offline";
//    }

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

    @GetMapping("/{type}/oauth/redirect") // type: google, kakao ...
    public void oauthLogin(HttpServletRequest request,
                           @PathVariable(value = "type") String type,
                           @RequestParam(value = "code") String authCode,
                           HttpServletResponse response) {
        System.out.println("authCode:: " + authCode);
        System.out.println("type:: " + type);
//         http://localhost:8080/api/kakao/oauth/redirect?code=OuelCZYTM-at2vDe0AHV3jX-M-v4mFlizzX-t-YP-QY_BmecifQkEHK3CpcKPXSXAAABjHfBXtTDukuslKNZWg
//        String requestURL = oauthService.request(type.toUpperCase());
//        response.sendRedirect(requestURL);
//        return "aaaaaa";
    }
}
