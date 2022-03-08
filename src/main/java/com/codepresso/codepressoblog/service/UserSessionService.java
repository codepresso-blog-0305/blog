package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.controller.dto.UserSessionResponseDto;
import com.codepresso.codepressoblog.mapper.UserSessionMapper;
import com.codepresso.codepressoblog.vo.UserSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@AllArgsConstructor
@Service
public class UserSessionService {
    private UserSessionMapper userSessionMapper;

    public UserSession getUserSessionById(Integer id) {
        return userSessionMapper.findOneById(id);
    }

    public Integer saveUserSession(Integer userId, String name) {
        UserSession userSession = new UserSession(userId, name);
        userSessionMapper.save(userSession);
        return userSession.getId();
    }

    public void deleteUserSession(Integer id) {
        userSessionMapper.delete(id);
    }

    public Integer getUserIdByCookie(HttpServletRequest request) {
        System.out.println("cookie start!");
        int session_id = 0;
        Cookie[] list = request.getCookies();
        for(Cookie cookie:list) {
            if(cookie.getName().equals("id")) {
                session_id = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        UserSession userSession = getUserSessionById(session_id);
        System.out.println("userSession.getUserId() = " + userSession.getUserId());
        System.out.println("bye!!");
        return userSession.getUserId();
    }
}
