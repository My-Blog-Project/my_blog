package com.hanghae.my_blog.entity;

import com.hanghae.my_blog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // 1씩 증가
    private Long id;

    // 제목
    @Column(nullable = false)
    private String title;

    // 유저명
    @Column(nullable = false)
    private String username;

    // 내용
    @Column(nullable = false)
    private String content;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
    }


    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.username = postRequestDto.getUsername();
        this.content = postRequestDto.getContent();
    }

}
