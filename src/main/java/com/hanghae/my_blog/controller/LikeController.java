package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Like API"})
@RequestMapping("/api")
public class LikeController {
    private final LikeService likeService;

    @PostMapping("/post/{postId}/like")
    @ApiOperation(value = "게시글 좋아요")
    public CompleteResponseDto likePost(@PathVariable Long postId, HttpServletRequest request) {
        return likeService.likePost(postId, request);
    }

    @PostMapping ("/comment/{commentId}/like")
    @ApiOperation(value = "댓글 좋아요")
    public CompleteResponseDto likeComment(@PathVariable Long commentId, HttpServletRequest request) {
        return likeService.likeComment(commentId, request);
    }

}