package com.hanghae.my_blog.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class SignupRequestDto {

    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9]).{4,10}$", message = "ID는 영어 소문자, 숫자 조합으로 4~10자만 가능합니다.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,15}$", message = "PW는 영어 소문자, 대문자, 숫자, 특수 문자 조합으로 8~15자만 가능합니다.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";

}
