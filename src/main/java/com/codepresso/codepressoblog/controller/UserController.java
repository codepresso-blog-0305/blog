package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.UserRequestDto;
import com.codepresso.codepressoblog.service.UserService;
import com.codepresso.codepressoblog.vo.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@RestController
public class UserController {
    private UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response) {
        Integer sessionId = userService.loginUser(userRequestDto.getUser());
        Cookie cookie = new Cookie("id", String.valueOf(sessionId));
        cookie.setMaxAge(60*60*24);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/user")
    public String saveUser(@RequestBody @Validated UserRequestDto requestDto) {
        userService.saveUser(requestDto.getUser());
        return "Success";
    }
}
