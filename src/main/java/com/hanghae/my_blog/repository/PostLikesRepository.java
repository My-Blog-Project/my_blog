package com.hanghae.my_blog.repository;

import com.hanghae.my_blog.entity.Post;
import com.hanghae.my_blog.entity.PostLikes;
import com.hanghae.my_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikesRepository extends JpaRepository<PostLikes, Long> {

    Long countByPostAndLikeCheckIsTrue(Post post);
    Optional<PostLikes> findByUserAndPostAndLikeCheckIsTrue(User user, Post post);
    Optional<PostLikes> findByUserAndPost(User user, Post post);
}
