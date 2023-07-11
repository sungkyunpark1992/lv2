package com.example.lv2bro.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creatAt;

    @ManyToOne
    private User user;

    public Post(String title, String content, LocalDateTime creatAt, User user){
        this.title = title;
        this.content = content;
        this.creatAt = creatAt;
        this.user = user;
    }

    public Post (){}
}
