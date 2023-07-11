package com.example.lv2bro.service;

import com.example.lv2bro.dto.UserLoginRequestDto;
import com.example.lv2bro.dto.UserSignupRequestDto;
import com.example.lv2bro.entity.User;
import com.example.lv2bro.repository.UserRepository;
import com.example.lv2bro.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;//PasswordEncoder암호화를 할때 필요함
//    public UserService(UserRepository userRepository/*, PasswordEncoder passwordEncoder*/){
//        this.userRepository = userRepository;
////        this.passwordEncoder = passwordEncoder;
//    }
    private final JwtUtil jwtUtil;

    public String signup(UserSignupRequestDto requestDto) {
        String username = requestDto.getUsername();
//        String password = passwordEncoder.encode(requestDto.getPassword());
        String password = requestDto.getPassword();

        //Optional null값을 허용함 id가 없을수도 있으니 Optional로 감싸준것 (래퍼타입)
        Optional<User> checkUsername = userRepository.findByUsername(username);
        // RequestDto -> Entity
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        User user = new User(requestDto);
        // DB 저장
        User saveUser = userRepository.save(user);
        return requestDto.getUsername();
    }


    public String login(UserLoginRequestDto requestDto, HttpServletResponse httpServletResponse) {
        Optional<User> user = userRepository.findByUsername(requestDto.getUsername());
        if(user.get().getPassword().equals(requestDto.getPassword())){//비밀번호가 같다면 뭘 수행해야 하나? 토큰을 생성해줘야한다. ->헤더에 보내는데 2가지 방식이 있다. 쿠키에 실어보내는 방식으로 해보자
            String token = jwtUtil.createToken(user.get().getUsername(), user.get().getUserRole());
            jwtUtil.addJwtToCookie(token, httpServletResponse);
        }else{
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return requestDto.getUsername();
    }
}
