package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Comment {
    Integer id;
    Integer postId;
    String content;
    String username;
    Date createdAt;

    public Comment(Integer id, Integer postId, String content, String username) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.username = username;
    }
}
