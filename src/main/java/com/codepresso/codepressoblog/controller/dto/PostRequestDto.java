package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    Integer id;
    Integer userId;
    String title;
    String content;

    public Post getPost() {
        return new Post(this.id, this.userId, this.title, this.content);
    }
}
