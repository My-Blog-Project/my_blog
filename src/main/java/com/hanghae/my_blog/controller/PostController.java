package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.*;
import com.hanghae.my_blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Api(tags = {"Post API"})
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    // 전체 게시글 조회
    @GetMapping("/post")
    @ApiOperation(value = "전체 게시글 조회")
    public PostAllShowResponseDto getPosts() {
        return postService.getPosts();
    }

    // 게시글 상세 조회
    @GetMapping("/post/{id}")
    @ApiOperation(value = "게시글 상세 조회")
    public PostShowResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 게시글 작성
    @PostMapping("/post")
    @ApiOperation(value = "게시글 작성")
    public PostCreateResponseDto createPost(@Valid @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.createPost(requestDto, request);
    }

    // 게시글 수정
    @PutMapping("/post/{id}")
    @ApiOperation(value = "게시글 수정")
    public CompleteResponseDto updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    @ApiOperation(value = "게시글 삭제")
    public CompleteResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }
}
