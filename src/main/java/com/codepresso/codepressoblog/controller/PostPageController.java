package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostRequestDto;
import com.codepresso.codepressoblog.controller.dto.UserSessionResponseDto;
import com.codepresso.codepressoblog.service.CommentService;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.service.UserSessionService;
import com.codepresso.codepressoblog.vo.Comment;
import com.codepresso.codepressoblog.vo.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Controller
public class PostPageController {

    private PostService postService;
    private CommentService commentService;
    private UserSessionController userSessionController;
    private UserSessionService userSessionService;

    @RequestMapping("/post/{id}")
    public String getPostDetailPage(Model model, @PathVariable Integer id) {
        Post post = postService.getPostById(id);
        List<Comment> comments = commentService.getCommentListByPostIdAndPage(id, 1, 3);
        Integer commentCount = commentService.getCommentCountByPostId(id);

        model.addAttribute("post", post);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("comments", comments);
        return "post_datail";
    }

    @RequestMapping("/post/create")
    public String getPostCreatePage() {
        return "post_write";
    }

    @RequestMapping("/post/edit/{id}")
    public String getPostEditPage(Model model, @PathVariable Integer id, HttpServletRequest request) {
        int userId = userSessionService.getUserIdByCookie(request);
        Post post = postService.getPostById(id);
        if(post.getUserId() != userId) {
            throw new NoSuchElementException();
        }
        model.addAttribute("post", post);
        return "post_edit";
    }
}
