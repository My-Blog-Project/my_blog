package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.entity.*;
import com.hanghae.my_blog.jwt.JwtUtil;
import com.hanghae.my_blog.repository.*;
import com.hanghae.my_blog.util.UserUtil;
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
    private final UserUtil userUtil;


    @Transactional
    public CompleteResponseDto likePost(Long postId, HttpServletRequest request) {
        Post post = checkPost(postId);
        User user = userUtil.getUserInfo(request);

        PostLikes postLikes = postLikesRepository.findByUserAndPost(user, post).orElse(null);
        if (postLikes != null && postLikes.isLikeCheck()==true) {
            //기존에 회원이 좋아요를 눌렀던 경우 좋아요 취소
            postLikes.likeCancle();
            return new CompleteResponseDto("좋아요 취소!");
        } else if (postLikes != null) {
            //기존에 회원이 좋아요를 눌러서 데이터베이스에 있으나 좋아요 취소 됐던 케이스는 다시 좋아요
            postLikes.likepost();
            return new CompleteResponseDto("좋아요!");
        } else {
            //좋아요를 처음 누르는 케이스이므로 데이터베이스에 정보 저장
            PostLikes addPostLike = new PostLikes(user, post);
            postLikesRepository.save(addPostLike);
            return new CompleteResponseDto("좋아요!");
        }
    }

    @Transactional
    public CompleteResponseDto likeComment(Long commentId, HttpServletRequest request) {
        Comment comment = checkComment(commentId);
        User user = userUtil.getUserInfo(request);

        CommentLikes commentLikes = commentLikesRepository.findByUserAndComment(user, comment).orElse(null);
        if (commentLikes != null && commentLikes.isLikeCheck()==true) {
            //기존에 회원이 좋아요를 눌렀던 경우 좋아요 취소
            commentLikes.likeCancle();
            return new CompleteResponseDto("좋아요 취소!");
        } else if (commentLikes != null) {
            //기존에 회원이 좋아요를 눌러서 데이터베이스에 있으나 좋아요 취소 됐던 케이스는 다시 좋아요
            commentLikes.likeComment();
            return new CompleteResponseDto("좋아요!");
        } else {
            //좋아요를 처음 누르는 케이스이므로 데이터베이스에 정보 저장
            CommentLikes addCommentLike = new CommentLikes(user, comment);
            commentLikesRepository.save(addCommentLike);
            return new CompleteResponseDto("좋아요!");
        }
    }


    private Post checkPost(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("포스트가 존재하지 않습니다.")
        );
    }

    private Comment checkComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
    }
}
