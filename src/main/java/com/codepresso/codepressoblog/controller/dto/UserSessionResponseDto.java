package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.UserSession;
import lombok.Getter;

@Getter
public class UserSessionResponseDto {
    Integer id;
    Integer userId;
    String name;

    public UserSessionResponseDto(UserSession userSession) {
        this.id = userSession.getId();
        this.userId = userSession.getUserId();
        this.name = userSession.getName();
    }
}
