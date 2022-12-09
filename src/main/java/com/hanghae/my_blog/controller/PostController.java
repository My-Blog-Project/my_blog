package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.*;
import com.hanghae.my_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public PostAllShowResponseDto getPosts() {
        return postService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/post/{id}")
    public PostShowResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 작성
    @PostMapping("/post")
    public PostCreateResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 게시글 수정
    @PutMapping("/post/{id}")
    public PostUpdateResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public PostDeleteResponseDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
