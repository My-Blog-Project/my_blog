package com.hanghae.my_blog.controller;

import com.hanghae.my_blog.dto.LoginRequestDto;
import com.hanghae.my_blog.dto.SignupRequestDto;
import com.hanghae.my_blog.service.UserService;
import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.dto.ResponseDto;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getAllErrors().get(0).getDefaultMessage();
            return new ResponseDto(msg, HttpStatus.BAD_REQUEST.value());
        }
        return userService.signup(requestDto);
    }

    @PostMapping("/login")
    public CompleteResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        return userService.login(loginRequestDto, response);
    }
}
