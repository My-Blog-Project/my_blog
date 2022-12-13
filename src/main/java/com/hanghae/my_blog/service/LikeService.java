package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.entity.*;
import com.hanghae.my_blog.jwt.JwtUtil;
import com.hanghae.my_blog.repository.*;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostLikesRepository postLikesRepository;
    private final CommentLikesRepository commentLikesRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    @Transactional
    public CompleteResponseDto likePost(Long id, HttpServletRequest request) {
        Post post = checkPost(id);
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        // postLikes db에 조건으로 준 userid && postid && LikeCheck=true 인 경우 == 해당 유저가 해당 게시글에 좋아요를 눌렀던 경우
        PostLikes postLikes1 = postLikesRepository.findByUserAndPostAndLikeCheckIsTrue(user, post).orElse(null);
        // postLike db에 조건으로 준 userid && postid 가 존재하는 경우 == 해당 유저가 좋아요를 누른적이 있으나 현재 LikeCheck=false인 경우
        PostLikes postLikes2 = postLikesRepository.findByUserAndPost(user, post).orElse(null);
        if (postLikes1 != null) {
            postLikes1.likeCancle();
            return new CompleteResponseDto("좋아요 취소!");
        } else if (postLikes2 != null) {
            postLikes2.likepost();
            return new CompleteResponseDto("좋아요!");
        } else {        //postLike db에 조건으로 준 userid && postid 데이터가 없는 경우 db에 새로 추가
            PostLikes addPostLike = new PostLikes(user, post);
            postLikesRepository.save(addPostLike);
            return new CompleteResponseDto("좋아요!");
        }
    }

    @Transactional
    public CompleteResponseDto likeComment(Long id, HttpServletRequest request) {
        Comment comment = checkComment(id);
        String token = jwtUtil.resolveToken(request);
        Claims claims = null;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("토큰이 유효하지 않습니다.");
            }
        }
        User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        // CommentLikes db에 조건으로 준 userid && commentid && LikeCheck=true 인 경우 == 해당 유저가 해당 댓글에 좋아요를 눌렀던 경우
        CommentLikes commentLikes1 = commentLikesRepository.findByUserAndCommentAndLikeCheckIsTrue(user, comment).orElse(null);
        // CommentLike db에 조건으로 준 userid && commentid 가 존재하는 경우 == 해당 유저가 좋아요를 누른적이 있으나 현재 LikeCheck=false인 경우
        CommentLikes commentLikes2 = commentLikesRepository.findByUserAndComment(user, comment).orElse(null);
        if (commentLikes1 != null) {
            commentLikes1.likeCancle();
            return new CompleteResponseDto("좋아요 취소!");
        } else if (commentLikes2 != null) {
            commentLikes2.likeComment();
            return new CompleteResponseDto("좋아요!");
        } else {        //CommentLike db에 조건으로 준 userid && commentid 데이터가 없는 경우 db에 새로 추가
            CommentLikes addCommentLike = new CommentLikes(user, comment);
            commentLikesRepository.save(addCommentLike);
            return new CompleteResponseDto("좋아요!");
        }
    }


    private Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("포스트가 존재하지 않습니다.")
        );
    }

    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("댓글이 존재하지 않습니다.")
        );
    }
}
