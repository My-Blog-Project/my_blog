package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.dto.CommentResponseDto;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/{postid}/comment")
    public CommentResponseDto saveComment(@PathVariable Long postid, @RequestBody CommentRequestDto commentRequsetDto, HttpServletRequest httpServletRequest){
        return commentService.saveComment(postid, commentRequsetDto, httpServletRequest);
    }
    //댓글 수정
    @PutMapping("/{postid}/{commentid}")
    public CommentResponseDto updateComment(@PathVariable Long postid, @PathVariable Long commentid, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.updateComment(postid, commentid, commentRequestDto, httpServletRequest);
    }
    //댓글 삭제
    @DeleteMapping("/{postid}/{commentid}")
    public CompleteResponseDto deleteComment(@PathVariable Long postid, @PathVariable Long commentid, HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(postid, commentid, httpServletRequest);
    }
}
