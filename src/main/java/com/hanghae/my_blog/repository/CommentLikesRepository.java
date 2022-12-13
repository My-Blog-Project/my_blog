package com.hanghae.my_blog.repository;

import com.hanghae.my_blog.entity.Comment;
import com.hanghae.my_blog.entity.CommentLikes;
import com.hanghae.my_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikesRepository extends JpaRepository<CommentLikes, Long> {

    Long countByCommentAndLikeCheckIsTrue(Comment comment);
    Optional<CommentLikes> findByUserAndCommentAndLikeCheckIsTrue(User user, Comment comment);
    Optional<CommentLikes> findByUserAndComment(User user, Comment comment);

}
