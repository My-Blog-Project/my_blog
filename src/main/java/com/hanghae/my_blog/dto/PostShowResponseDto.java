package com.hanghae.my_blog.dto;

import com.hanghae.my_blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostShowResponseDto {
    private Long num;
    private String username;
    private String content;
    private String title;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList = new ArrayList<>();

    public PostShowResponseDto(Post post) {
        this.num = post.getId();
        this.title = post.getTitle();
        this.username = post.getUser().getUsername();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostShowResponseDto(Post post, CommentListResponseDto commentList) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.commentList = commentList.getCommentList();
    }
}
