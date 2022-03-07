package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.CommentRequestDto;
import com.codepresso.codepressoblog.controller.dto.CommentResponseDto;
import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
public class CommentController {
    private CommentService commentService;

    @GetMapping("/comment")
    public List<CommentResponseDto> getCommentListByPost(@RequestParam("post_id") Integer postId, @RequestParam Integer page) {
        List<Comment> comments = commentService.getCommentListByPostIdAndPage(postId, page, 3);

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for(Comment comment : comments) {
            commentResponseDtos.add(new CommentResponseDto(comment));
        }

        return commentResponseDtos;
    }

    @PostMapping("/comment")
    public String createComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.saveComment(comment);

        return "success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody CommentRequestDto commentDto) {
        Comment comment = commentDto.getComment();
        commentService.updateComment(comment);

        return "success";
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Integer id) {
        commentService.deleteComment(id);

        return "success";
    }
}
