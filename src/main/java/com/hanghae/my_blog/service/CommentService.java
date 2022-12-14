package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.dto.CommentResponseDto;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.entity.Comment;
import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.entity.User;
import com.hanghae.my_blog.entity.UserRoleEnum;
import com.hanghae.my_blog.jwt.JwtUtil;
import com.hanghae.my_blog.repository.CommentRepository;
import com.hanghae.my_blog.repository.PostRepository;
import com.hanghae.my_blog.repository.UserRepository;
import com.hanghae.my_blog.util.UserUtil;
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
    private final UserUtil userUtil;

    @Transactional
    //댓글 저장하기
    public CommentResponseDto saveComment(Long postId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //게시글 저장 여부 확인
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다")
        );
        //댓글 저장
        Comment comment = new Comment(commentRequestDto,  post, user);
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }
    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //게시글 존재 여부 확인
        if(!postRepository.existsById(postId)){
            throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }
        //댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(("댓글이 존재하지 않습니다")
        ));
        //ADMIN 권한, username 확인 후 댓글 업데이트
        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
        comment.update(commentRequestDto);
        }else{
            throw new IllegalArgumentException("올바른 사용자가 아닙니다");
        }
        //수정된 댓글 반환
        return new CommentResponseDto(comment);
    }
    @Transactional
    //댓글 삭제하기
    public CompleteResponseDto deleteComment(Long postId, Long commentId, HttpServletRequest httpServletRequest) {
        //로그인 여부 확인
        User user = userUtil.getUserInfo(httpServletRequest);
        //게시글 저장 여부 확인
        if(!postRepository.existsById(postId)){
            throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }
        //댓글 저장 여부 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException(("댓글이 존재하지 않습니다")
        ));
        //ADMIN 권한, username 확인 후 댓글 삭제
        if(comment.getUser().getUsername().equals(user.getUsername()) || user.getRole() == UserRoleEnum.ADMIN){
            commentRepository.delete(comment);
        }else{
            throw new IllegalArgumentException("올바른 사용자가 아닙니다");
        }
        //삭제 완료 반환
        return new CompleteResponseDto("삭제 완료");
    }
}
