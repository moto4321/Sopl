package com.sopl.sopl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    @RequestMapping("/kakaoLogin")
    public String kakaoLoginPage() {
//        log.info("session:: " + session);
        return "oauth/kakaoLogin";
    }
}
