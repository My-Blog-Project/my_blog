package com.hanghae.my_blog.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class CommentListResponseDto {
    List<CommentResponseDto> commentList = new ArrayList<>();
    public void addComment(CommentResponseDto commentResponseDto) {
        commentList.add(commentResponseDto);
    }
}
