package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.dto.CommentResponseDto;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
@Api(tags = {"Comment API"})
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 생성
    @PostMapping("/{postId}")
    @ApiOperation(value = "댓글 작성")
    public CommentResponseDto saveComment(@PathVariable Long postId, @Valid @RequestBody CommentRequestDto commentRequsetDto, HttpServletRequest httpServletRequest){

        return commentService.saveComment(postId, commentRequsetDto, httpServletRequest);
    }
    //댓글 수정
    @PutMapping("/{postId}/{commentId}")
    @ApiOperation(value = "댓글 수정")
    public CommentResponseDto updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.updateComment(postId, commentId, commentRequestDto, httpServletRequest);
    }
    //댓글 삭제
    @DeleteMapping("/{postId}/{commentId}")
    @ApiOperation(value = "댓글 삭제")
    public CompleteResponseDto deleteComment(@PathVariable Long postId, @PathVariable Long commentId, HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(postId, commentId, httpServletRequest);
    }
}
