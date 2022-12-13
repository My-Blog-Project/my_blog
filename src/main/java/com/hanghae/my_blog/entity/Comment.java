package com.hanghae.my_blog.entity;

import com.hanghae.my_blog.dto.CommentRequestDto;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
	private List<CommentLikes> commentLikes = new ArrayList<>();

	public Comment(CommentRequestDto requestDto, Post post, User user) {
		this.content = requestDto.getContent();
		this.post = post;
		this.user = user;
	}

	public void update(CommentRequestDto commentRequestDto) {
		this.content = commentRequestDto.getContent();
	}
}
