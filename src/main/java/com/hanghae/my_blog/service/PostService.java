package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.*;
import com.hanghae.my_blog.entity.Comment;
import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.entity.User;
import com.hanghae.my_blog.repository.CommentRepository;
import com.hanghae.my_blog.repository.PostRepository;
import com.hanghae.my_blog.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserUtil userUtil;

    // 전체 포스트 가져오기
    @Transactional(readOnly = true)
    public PostAllShowResponseDto getPosts() {
        PostAllShowResponseDto postAllShowResponseDto = new PostAllShowResponseDto();

        List<Post> posts = postRepository.findAllByOrderByModifiedAtDesc();
        for(Post post : posts) {
            CommentListResponseDto commentListRequestDto = new CommentListResponseDto();
            List<Comment> comments = commentRepository.findByPost_IdOrderByCreatedAtDesc(post.getId());
            for(Comment comment : comments) {
                commentListRequestDto.addComment(new CommentResponseDto(comment));
            }
            postAllShowResponseDto.addPost(new PostResponseDto(post, commentListRequestDto));
        }

        return postAllShowResponseDto;
    }

    // 선택 포스트 가져오기
    @Transactional(readOnly = true)
    public PostShowResponseDto getPost(Long id) {
        Post post = checkPost(id);

        CommentListResponseDto commentListRequestDto = new CommentListResponseDto();
        List<Comment> comments = commentRepository.findByPost_IdOrderByCreatedAtDesc(post.getId());
        for(Comment comment : comments) {
            commentListRequestDto.addComment(new CommentResponseDto(comment));
        }

        return new PostShowResponseDto(post, commentListRequestDto);
    }

    // 포스트 생성
    @Transactional
    public PostCreateResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {
        User user = userUtil.getUserInfo(request);
        Post post = new Post(requestDto, user);

        postRepository.save(post); // 자동으로 쿼리가 생성되면서 데이터베이스에 연결되며 저장된다.

        return new PostCreateResponseDto(post);
    }

    // 포스트 수정
    @Transactional
    public ResponseDto updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        Post post = checkPost(id);
        userUtil.getUserInfo(request);

        post.update(requestDto);
        postRepository.save(post);

        return new ResponseDto("포스트 수정 완료", HttpStatus.OK.value());
    }


    // 포스트 삭제
    @Transactional
    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        Post post = checkPost(id);
        userUtil.getUserInfo(request);

        postRepository.delete(post);

        return new ResponseDto("포스트 삭제 성공", HttpStatus.OK.value());

    }

    // 포스트 번호를 체크해서 번호가 없으면 에러메세지 출력
    private Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("포스트가 존재하지 않습니다.")
        );
    }


}
