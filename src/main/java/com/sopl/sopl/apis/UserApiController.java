package com.sopl.sopl.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {
    // /api/google/oauth/redirect

    @Value("${google.client.id}")
    private String googleClientId;
    @Value("${google.client.pw}")
    private String googleClientPw;
    @Value("${google.redirectUrl}")
    private String googleRedirectUrl;

    @PostMapping("/v1/oauth2/google")
    public String googleLogin(){
        return "https://accounts.google.com/o/oauth2/v2/auth?client_id=" +
                googleClientId +
                "&redirect_uri=" +
                googleRedirectUrl +
                "&response_type=code&scope=email%20profile%20openid&access_type=offline";
    }
}
