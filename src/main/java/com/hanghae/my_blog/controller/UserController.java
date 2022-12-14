package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.LoginRequestDto;
import com.hanghae.my_blog.dto.SignupRequestDto;
import com.hanghae.my_blog.service.UserService;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Api(tags = {"User API"})
@RestController
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
//    @ApiOperation(value = "회원가입")
    public CompleteResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto) {
        return userService.signup(requestDto);
    }


    @PostMapping("/login")
//    @ApiOperation(value = "로그인")
    public CompleteResponseDto login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
