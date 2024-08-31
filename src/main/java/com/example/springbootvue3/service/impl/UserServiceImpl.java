package com.example.springbootvue3.service.impl;

import com.example.springbootvue3.entity.User;
import com.example.springbootvue3.mapper.UserMapper;
import com.example.springbootvue3.service.UserService;
import com.example.springbootvue3.utils.Md5Util;
import com.example.springbootvue3.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Boolean addNewUser(String username,String password, LocalDateTime createTime, LocalDateTime updateTime) {
        return userMapper.addNewUser(username,password, createTime, updateTime);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public User findByUserId(int id) {
        return userMapper.findByUserId(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int updateAvatar(String avatarUrl) {
        Map<String,Object> o = ThreadLocalUtil.get();
        int idx = (int)o.get("id");
        return userMapper.updateAvatar(avatarUrl,idx);
    }

    @Override
    public int updatePwd(String password, int id) {
        return userMapper.updatePwd(password,id);
    }

    public Boolean register(String username,String password){
        String md5String = Md5Util.getMD5String(password);
        return addNewUser(username,md5String,LocalDateTime.now(),LocalDateTime.now());
    }
}
