package com.example.springbootvue3.service;

import com.example.springbootvue3.entity.User;

import java.time.LocalDateTime;

public interface UserService {
    Boolean addNewUser(String username, String password, LocalDateTime createTime, LocalDateTime updateTime);
    User findByUsername(String username);
    User findByUserId(int id);
    int update(User user);
    int updateAvatar(String avatarUrl);
    int updatePwd(String password,int id);
}
