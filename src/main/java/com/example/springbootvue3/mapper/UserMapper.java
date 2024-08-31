package com.example.springbootvue3.mapper;

import com.example.springbootvue3.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
@Mapper
public interface UserMapper {
    @Insert("insert into user(username,password,create_time,update_time) values (#{username},#{password},#{createTime},#{updateTime})")
    boolean addNewUser(String username, String password, LocalDateTime createTime, LocalDateTime updateTime);
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
    @Select("select * from user where id = #{id}")
    User findByUserId(int id);
    @Update("update user set nickname = #{nickname},email = #{email}, update_time = now() where id = #{id}")
    int update(User user);
    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    int updateAvatar(String avatarUrl, int id);
    @Update("update user set password = #{password}, update_time = now() where id = #{id}")
    int updatePwd(String password,int id);
}
