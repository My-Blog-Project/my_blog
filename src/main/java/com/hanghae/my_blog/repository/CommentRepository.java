package com.hanghae.my_blog.repository;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.entity.Comment;
import com.hanghae.my_blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 외래키 검색 규칙
    // findBy + {fk를 관리하는 entity의 필드명, 첫 글자를 대문자} + _ + {fk entity의 식별자 필드명, 첫 글자를 대문자}
    List<Comment> findByPost_IdOrderByCreatedAtDesc(Long postId);

    @Query("SELECT DISTINCT c from Comment c join fetch c.user where c.post.id = :postId order by c.modifiedAt desc")
    List<Comment> findAllByPostIdWithUser(@Param("postId")Long postId);

}
