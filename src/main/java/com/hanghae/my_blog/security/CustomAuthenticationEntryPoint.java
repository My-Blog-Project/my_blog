package com.hanghae.my_blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.my_blog.dto.SecurityExceptionDto;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final SecurityExceptionDto exceptionDto =
		new SecurityExceptionDto("인증에 실패하였습니다.", HttpStatus.UNAUTHORIZED.value());

	@Override
	public void commence(HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authenticationException) throws IOException {

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());

		try (OutputStream os = response.getOutputStream()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(os, exceptionDto);
			os.flush();
		}
	}
}
