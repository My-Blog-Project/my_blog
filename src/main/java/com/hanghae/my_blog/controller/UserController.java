package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.SignupRequestDto;
import com.hanghae.my_blog.service.UserService;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public CompleteResponseDto signup(@RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }
}
