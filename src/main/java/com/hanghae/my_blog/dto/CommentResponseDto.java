package com.hanghae.my_blog.dto;

import com.hanghae.my_blog.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    private Long num;
    private String username;

    private String content;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long likeCount=0l;

    public CommentResponseDto(Comment comment) {
        this.num = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public CommentResponseDto(Comment comment, Long commentLikeCnt) {
        this.num = comment.getId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.likeCount = commentLikeCnt;
    }

}
