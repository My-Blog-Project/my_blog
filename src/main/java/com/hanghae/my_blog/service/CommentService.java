package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.dto.CommentResponseDto;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.entity.Comment;
import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.entity.User;
import com.hanghae.my_blog.repository.CommentRepository;
import com.hanghae.my_blog.repository.PostRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final JwtUtil jwtUtil;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    //댓글 저장하기
    public CommentResponseDto saveComment(Long postid, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new NullPointerException("게시글이 존재하지 않습니다")
        );
        //댓글 저장
        Comment comment = new Comment(commentRequestDto,  post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment.getContent());
    }
    @Transactional
    public CommentResponseDto updateComment(Long postid, Long commentid, CommentRequestDto commentRequestDto, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 존재 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new NullPointerException(("게시글이 존재하지 않습니다")
        ));
        //댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new NullPointerException(("댓글이 존재하지 않습니다")
        ));
        //댓글 업데이트
        comment.update(commentRequestDto);
        //수정된 댓글 반환
        return new CommentResponseDto(comment.getContent());
    }
    //댓글 삭제하기
    public CompleteResponseDto deleteComment(Long postid, Long commentid, HttpServletRequest request) {
        //로그인 여부 확인
        User user = tokenChecking(request);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postid).orElseThrow(
                () -> new NullPointerException(("게시글이 존재하지 않습니다")
        ));
        //댓글 저장 여부 확인
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new NullPointerException(("댓글이 존재하지 않습니다")
        ));
        //댓글 삭제
        commentRepository.delete(comment);
        //삭제 완료 반환
        return new CompleteResponseDto("삭제 완료");
    }
    //로그인 여부 확인
    public User tokenChecking(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new NullPointerException(("사용자가 존재하지 않습니다")
            ));
            return user;
        }throw new IllegalArgumentException("로그인이 필요합니다");

    }
}
