package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post/{postId}/like")
    public CompleteResponseDto likePost(@PathVariable Long postId, HttpServletRequest request) {
        return likeService.likePost(postId, request);
    }

    @PostMapping ("/comment/{commentId}/like")
    public CompleteResponseDto likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        return likeService.likeComment(commentId, request);
    }

}