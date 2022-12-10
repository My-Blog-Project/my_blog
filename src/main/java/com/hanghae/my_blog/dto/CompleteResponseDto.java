package com.hanghae.my_blog.dto;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CompleteResponseDto {
	private String msg;
	private int statusCode;

	public CompleteResponseDto(String msg) {
		this.msg = msg;
		this.statusCode = HttpStatus.OK.value();
	}
}
