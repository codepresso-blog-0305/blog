package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDto {
    String name;
    String email;
    String password;

    public User getUser() {
        return new User(name, email, password);
    }
}
