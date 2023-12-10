package com.sopl.sopl.apis;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {


    @Value("${oauth.google.client.id}")
    private String googleClientId;
    @Value("${oauth.google.client.pw}")
    private String googleClientPw;
    @Value("${oauth.google.redirectUrl}")
    private String googleRedirectUrl;

    @PostMapping("/v1/oauth/google")
    public String getGoogleLoginPage(){
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=" +
                googleClientId +
                "&redirect_uri=" +
                googleRedirectUrl +
                "&response_type=code&scope=email%20profile%20openid&access_type=offline";
    }

    @GetMapping("/google/oauth/redirect") // type: google, kakao ...
    public void oauthLogin(HttpServletRequest request,
//                              @PathVariable(value = "type") String type,
                              @RequestParam(value = "code") String authCode,
                              HttpServletResponse response) {
        System.out.println("authCode:: " + authCode);
//        String requestURL = oauthService.request(type.toUpperCase());
//        response.sendRedirect(requestURL);
//        return "aaaaaa";
    }
}
