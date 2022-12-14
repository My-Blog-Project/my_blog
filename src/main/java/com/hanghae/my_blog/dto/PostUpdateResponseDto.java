package com.hanghae.my_blog.dto;

import com.hanghae.my_blog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class PostUpdateResponseDto {
    private Long num;
    private String username;
    private String content;
    private String title;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private Long likeCount;

    public PostUpdateResponseDto(Post post, Long postLikeCnt) {
        this.num = post.getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.likeCount = postLikeCnt;
    }
}
