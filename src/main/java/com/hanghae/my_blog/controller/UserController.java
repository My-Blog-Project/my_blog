package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.LoginRequestDto;
import com.hanghae.my_blog.dto.SignupRequestDto;
import com.hanghae.my_blog.service.UserService;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public CompleteResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public CompleteResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
