package com.codepresso.codepressoblog.service;

import com.codepresso.codepressoblog.mapper.UserMapper;
import com.codepresso.codepressoblog.vo.User;
import com.codepresso.codepressoblog.vo.UserSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserService {
    private UserSessionService userSessionService;
    private UserMapper userMapper;

    public void saveUser(User user) {
        userMapper.save(user);
    }

    public Integer loginUser(User user) {
        User userResult = getUserByEmailAndPassword(user.getEmail(), user.getPassword());
        Integer sessionId = userSessionService.saveUserSession(userResult.getId(), userResult.getName());
        return sessionId;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = userMapper.findOneByEmailAndPassword(email, password);
        if(user == null) {
            throw new NoSuchElementException();
        }

        return user;
    }
}
