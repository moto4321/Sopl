package com.sopl.sopl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class AuthController {

    @GetMapping("loginPage")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/oauth2/authorization/google")
    public void test() {
        log.info("123123123");
    }
}
