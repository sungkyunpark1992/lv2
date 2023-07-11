package com.example.lv2bro.entity;

import com.example.lv2bro.dto.UserSignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User {
    @Id//이게 pk다
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 이게 pk다(고유값)
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;

//    public User(String username, String password){
//        this.username = username;
//        this.password = password;
//    }
    public User(UserSignupRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
    }//생성자 오버로딩

    public User(){}
}
