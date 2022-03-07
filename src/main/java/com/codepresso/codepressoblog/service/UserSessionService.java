package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.UserSessionMapper;
import com.codepresso.codepressoblog.vo.UserSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
