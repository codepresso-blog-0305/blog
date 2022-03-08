package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.CommentRequestDto;
import com.codepresso.codepressoblog.controller.dto.CommentResponseDto;
import com.codepresso.codepressoblog.controller.dto.UserSessionResponseDto;
import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.service.UserSessionService;
import com.codepresso.codepressoblog.vo.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
public class CommentController {
    private CommentService commentService;
    private UserSessionController userSessionController;
    private UserSessionService userSessionService;


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
        int session_id = commentDto.getUserId();
        UserSessionResponseDto userSession = userSessionController.getSession(session_id);

        commentDto.setUserId(userSession.getUserId());
        commentDto.setUsername(userSession.getName());

        Comment comment = commentDto.getComment();
        commentService.saveComment(comment);

        return "success";
    }

    @PutMapping("/comment")
    public String updateComment(@RequestBody CommentRequestDto commentDto, HttpServletRequest request) {
        int userId = userSessionService.getUserIdByCookie(request);
        Comment comment = commentDto.getComment();

        if(comment.getUserId() != userId) {
            throw new NoSuchElementException();
        }

        commentService.updateComment(comment);

        return "success";
    }

    @DeleteMapping("/comment")
    public String deleteComment(@RequestParam Integer id, HttpServletRequest request) {
        int userId = userSessionService.getUserIdByCookie(request);

        //id -> comment_id 이걸로 comment의 user_id?
        Integer commentUserID = commentService.findByCommentId(id);

        if(commentUserID != userId) {
            throw new NoSuchElementException();
        }

        commentService.deleteComment(id);

        return "success";
    }
}
