package com.hanghae.my_blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SecurityExceptionDto {
	private String msg;
	private int statusCode;

	public SecurityExceptionDto(String msg, int statusCode) {
		this.msg = msg;
		this.statusCode = statusCode;
	}
}
