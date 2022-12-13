package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.*;
import com.hanghae.my_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


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
    @GetMapping("/posts/{id}")
    public PostShowResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 작성
    @PostMapping("/posts")
    public PostCreateResponseDto createPost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseDto deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
