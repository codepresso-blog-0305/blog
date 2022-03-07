package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {
    Integer id;
    Integer userId;
    String title;
    String content;
    String email;
    String name;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.email = post.getUser().getEmail();
        this.name = post.getUser().getName();
    }
}
