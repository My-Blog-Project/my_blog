package com.hanghae.my_blog.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostAllShowResponseDto {
    List<PostResponseDto> postList = new ArrayList<>();

    public void addPost(PostResponseDto responseDto) {postList.add(responseDto);}
}
