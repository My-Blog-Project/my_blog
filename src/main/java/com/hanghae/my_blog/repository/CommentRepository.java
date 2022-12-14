package com.hanghae.my_blog.repository;

import com.hanghae.my_blog.dto.CommentRequestDto;
import com.hanghae.my_blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 외래키 검색 규칙
    // findBy + {fk를 관리하는 entity의 필드명, 첫 글자를 대문자} + _ + {fk entity의 식별자 필드명, 첫 글자를 대문자}
    List<Comment> findByPost_IdOrderByCreatedAtDesc(Long postId);

    @Modifying // update delete쿼리만 사용
    @Query("delete from Comment c where c.id in :commentid")
    void deleteByCommentId(@Param("commentid") List<Long> id);

    @Query("select c.id FROM Comment c where c.post.id = :postid")
    List<Long> findIdsByPostId(@Param("postid") Long id);

}
