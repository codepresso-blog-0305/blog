package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.service.UserSessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Controller
public class UserPageController {
    private UserSessionService userSessionService;

    @RequestMapping("/user/login")
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping("/user/signup")
    public String getSignupPage() {
        return "signup";
    }

}
