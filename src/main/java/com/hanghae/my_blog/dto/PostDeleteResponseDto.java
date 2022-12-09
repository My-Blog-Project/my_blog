package com.hanghae.my_blog.dto;

import lombok.Getter;

@Getter
public class PostDeleteResponseDto {
    private String msg;
    private int statusCode;

    public PostDeleteResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
