package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostRequestDto;
import com.codepresso.codepressoblog.controller.dto.PostResponseDto;
import com.codepresso.codepressoblog.controller.dto.UserSessionResponseDto;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.service.UserSessionService;
import com.codepresso.codepressoblog.vo.Post;
import com.codepresso.codepressoblog.vo.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PostController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private PostService postService;
    private UserSessionController userSessionController;
    private UserSessionService userSessionService;

    public PostController(PostService postService, UserSessionController userSessionController, UserSessionService userSessionService) {
        this.postService = postService;
        this.userSessionController = userSessionController;
        this.userSessionService = userSessionService;
    }

    @GetMapping("/post/all")
    public List<PostResponseDto> getAllPostList() {
        List<Post> postList = postService.getAllPost();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post : postList) {
            postResponseDtos.add(new PostResponseDto(post));
        }

        return postResponseDtos;
    }

    @GetMapping("/post")
    public List<PostResponseDto> getPostList(@RequestParam Integer page) {
        List<Post> postList = postService.getPostListByPage(page, 3);

        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for(Post post : postList) {
            postResponseDtos.add(new PostResponseDto(post));
        }

        return postResponseDtos;
    }

    @PostMapping("/post")
    public String createPost(@RequestBody @Validated PostRequestDto postDto) {
        //session id -> user_id
        int session_id = postDto.getUserId();
        UserSessionResponseDto userSession = userSessionController.getSession(session_id);

        postDto.setUserId(userSession.getUserId());

        Post post = postDto.getPost();
        postService.savePost(post);

        return "success";
    }

    @PutMapping("/post")
    public ResponseEntity updatePost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.updatePost(post);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Integer id, HttpServletRequest request) {
        int userId = userSessionService.getUserIdByCookie(request);
        Post post = postService.getPostById(id);

        System.out.println("post.getUserId() = " + post.getUserId());
        System.out.println("userId = " + userId);
        if(post.getUserId() != userId) {
            throw new NoSuchElementException();
        }

        postService.deletePost(id);

        return "success";
    }

}
