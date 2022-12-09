package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.*;
import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 전체 포스트 가져오기
    @Transactional(readOnly = true)
    public PostAllShowResponseDto getPosts() {
        PostAllShowResponseDto postAllShowResponseDto = new PostAllShowResponseDto();

        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        for(Post post : posts) {
            postAllShowResponseDto.addPost(new PostResponseDto(post));
        }

        return postAllShowResponseDto;
    }

    // 선택 포스트 가져오기
    @Transactional(readOnly = true)
    public PostShowResponseDto getPost(Long id) {
        Post post = checkPost(id);

        return new PostShowResponseDto(post);
    }

    // 포스트 생성
    public PostCreateResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post); // 자동으로 쿼리가 생성되면서 데이터베이스에 연결되며 저장된다.

        return new PostCreateResponseDto(post);
    }

    // 포스트 수정
    @Transactional
    public PostUpdateResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = checkPost(id);

        post.update(requestDto);
        postRepository.save(post);

        return new PostUpdateResponseDto("포스트 수정 완료", HttpStatus.OK.value());
    }


    // 포스트 삭제
    @Transactional
    public PostDeleteResponseDto deletePost(Long id) {
        Post post = checkPost(id);

        postRepository.delete(post);

        return new PostDeleteResponseDto("포스트 삭제 성공", HttpStatus.OK.value());

    }

    // 포스트 번호를 체크해서 번호가 없으면 에러메세지 출력
    private Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("포스트가 존재하지 않습니다.")
        );
    }

}
