package com.sopl.sopl.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    public Boolean ping() {
        return true;
    }

    @GetMapping("/notPing")
    public Boolean notPing() {
        return true;
    }
}
