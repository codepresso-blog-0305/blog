package com.codepresso.codepressoblog.controller;

import com.codepresso.codepressoblog.controller.dto.PostRequestDto;
import com.codepresso.codepressoblog.controller.dto.PostResponseDto;
import com.codepresso.codepressoblog.service.PostService;
import com.codepresso.codepressoblog.vo.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
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
        Post post = postDto.getPost();
        postService.savePost(post);

        return "success";
    }

    @PutMapping("/post")
    public String updatePost(@RequestBody PostRequestDto postDto) {
        Post post = postDto.getPost();
        postService.updatePost(post);

        return "success";
    }
}
