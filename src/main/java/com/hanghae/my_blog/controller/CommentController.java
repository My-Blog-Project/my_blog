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
    @PostMapping("/{postid}")
    @ApiOperation(value = "댓글 생성")
    public CommentResponseDto saveComment(@PathVariable Long postid, @Valid @RequestBody CommentRequestDto commentRequsetDto, HttpServletRequest httpServletRequest){

        return commentService.saveComment(postid, commentRequsetDto, httpServletRequest);
    }
    //댓글 수정
    @PutMapping("/{postid}/{commentid}")
    @ApiOperation(value = "댓글 수정")
    public CommentResponseDto updateComment(@PathVariable Long postid, @PathVariable Long commentid, @Valid @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.updateComment(postid, commentid, commentRequestDto, httpServletRequest);
    }
    //댓글 삭제
    @DeleteMapping("/{postid}/{commentid}")
    @ApiOperation(value = "댓글 삭제")
    public CompleteResponseDto deleteComment(@PathVariable Long postid, @PathVariable Long commentid, HttpServletRequest httpServletRequest) {
        return commentService.deleteComment(postid, commentid, httpServletRequest);
    }
}
