package com.example.lv2bro.repository;

import com.example.lv2bro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<User, Long> {
}
