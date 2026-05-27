package com.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("home called");
        return "Spring security 시작!";
    }

    @GetMapping("/home")
    public String auth(@AuthenticationPrincipal UserDetails user) {
        log.info("auth called");
        log.info("user = {}", user);
        log.info("user.getUsername() = {}", user.getUsername());
        return "Authentication Successful 페이지입니다!";
    }
}