package com.hanghae.my_blog.service;

import com.hanghae.my_blog.dto.CompleteResponseDto;
import com.hanghae.my_blog.dto.SignupRequestDto;
import com.hanghae.my_blog.dto.UserRoleEnum;
import com.hanghae.my_blog.entity.User;
import com.hanghae.my_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public CompleteResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()){
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 토큰값이 일치하지 않습니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, role);
        userRepository.save(user);

        return new CompleteResponseDto("회원가입 완료");


    }

}