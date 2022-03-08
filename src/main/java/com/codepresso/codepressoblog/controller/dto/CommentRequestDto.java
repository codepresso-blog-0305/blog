package com.codepresso.codepressoblog.controller.dto;

import com.codepresso.codepressoblog.vo.Comment;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentRequestDto {
    Integer id;
    Integer postId;
    String content;
    String username;
    Integer userId;

    public Comment getComment() {
        return new Comment(this.id, this.postId, this.content, this.username, this.userId);
    }
}
