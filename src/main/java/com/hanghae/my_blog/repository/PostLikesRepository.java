package com.hanghae.my_blog.repository;

import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.entity.PostLikes;
import com.hanghae.my_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {

    Long countByPostAndLikeCheckIsTrue(Post post);
    Optional<PostLikes> findByUserAndPostAndLikeCheckIsTrue(User user, Post post);
    Optional<PostLikes> findByUserAndPost(User user, Post post);

    @Modifying
    @Query("delete from PostLikes pl where pl.post.id = :postid")
    void deleteByPostId(@Param("postid") Long id);
}
