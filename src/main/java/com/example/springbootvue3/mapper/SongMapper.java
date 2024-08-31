package com.example.springbootvue3.mapper;

import com.example.springbootvue3.entity.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SongMapper {
    @Select("select * from Song where songId = #{id}")
    Song getSongById(int id);
}
