package com.hanghae.my_blog.entity;

import com.hanghae.my_blog.dto.UserRoleEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
	private String username;

	@Column(nullable = false,  columnDefinition = "VARCHAR(15)")
	private String password;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private UserRoleEnum role;

	public User(String username, String password, UserRoleEnum role) {
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
