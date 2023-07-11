package com.example.lv2bro.controller;

import com.example.lv2bro.dto.UserLoginRequestDto;
import com.example.lv2bro.dto.UserSignupRequestDto;
import com.example.lv2bro.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;//DI 생성자 주입 숙련주차1-7강의 17:00

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/auth/signup")
    public String signup(@RequestBody UserSignupRequestDto requestDto){//@RequestBodys는 json포스트맨에서 body부분
        String username = userService.signup(requestDto);
        return username + " Signup Success!";
    }
    @PostMapping("/auth/login")//회원가입이 post인건 이해가 되는데 로그인은 왜 post인걸까?
    public String login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse httpServletResponse){
        String username = userService.login(requestDto, httpServletResponse);
        return username + " Login Success!";
    }
}
