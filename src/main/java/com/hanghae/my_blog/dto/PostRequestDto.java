package com.hanghae.my_blog.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostRequestDto {
    private String username;
    private String title;
    private String content;
    private LocalDateTime registeredAt;
    private LocalDateTime unRegisteredAt;
}
