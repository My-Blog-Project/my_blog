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

    @PostMapping("/postlike/{id}")
    public CompleteResponseDto likePost(@PathVariable Long id, HttpServletRequest request) {
        return likeService.likePost(id, request);
    }

    @PostMapping ("/commentlike/{id}")
    public CompleteResponseDto likeComment(@PathVariable Long id, HttpServletRequest request) {
        return likeService.likeComment(id, request);
    }

}