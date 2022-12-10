package com.hanghae.my_blog.entity;

import com.hanghae.my_blog.dto.CommentRequestDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends Timestamped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommentLikes> commentLikes = new ArrayList<>();

	public Comment(CommentRequestDto requestDto) {
		this.content = requestDto.getContent();
	}

	public void setPost(Post post) {
		this.post = post;
		post.getComments().add(this);
	}

	public void setUser(User user) {
		this.user = user;
		user.getComments().add(this);
	}

	public void update(CommentRequestDto commentRequestDto) {
		this.content = commentRequestDto.getContent();
	}
}
