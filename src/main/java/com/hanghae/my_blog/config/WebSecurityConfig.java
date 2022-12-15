package com.hanghae.my_blog.config;

import com.hanghae.my_blog.jwt.JwtAuthFilter;
import com.hanghae.my_blog.jwt.JwtUtil;
import com.hanghae.my_blog.security.CustomAccessDeniedHandler;
import com.hanghae.my_blog.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

	private final JwtUtil jwtUtil;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()

			.antMatchers("/", "/api/auth/**").permitAll()
			.antMatchers(HttpMethod.GET, "/api/posts/**").permitAll()
			.antMatchers(HttpMethod.POST,"/api/loggin").permitAll()

			.antMatchers(HttpMethod.GET,"/swagger-resources/**").permitAll()
			.antMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
			.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

			.anyRequest().authenticated()
			.and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

		// 401 Error 처리, 인증과정에서 실패할 시 처리
		http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

		// 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
		http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

		return http.build();
	}
}
