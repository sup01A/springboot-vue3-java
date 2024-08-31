package com.example.springbootvue3.mapper;

import com.example.springbootvue3.entity.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ArticleMapper {
    @Select("select * from article")
    List<Article> getAll();
    @Insert("insert into article values (null,#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    int add(Article article);
    @Select("<script>" +
            "SELECT * FROM article " +
            "<where>" +
            "<if test='userId != null'>" +
            "create_user = #{userId} " +
            "</if>" +
            "<if test='categoryId != null'>" +
            "AND category_id = #{categoryId} " +
            "</if>" +
            "<if test='state != null and state.trim().length() > 0'>" +
            "AND state = #{state}" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Article> list(int userId, Integer categoryId,String state);
    @Update("update article set title = #{title},content = #{content}, cover_img = #{coverImg},state = #{state}, category_id = #{categoryId} where id =#{id}")
    int update(Article article);
    @Delete("delete from article where id = #{id}")
    int delete(int id);
    @Select("select * from article where id = #{id}")
    Article detail(int id);
    @Select("select * from article where category_id = #{categoryId} and state = #{state}")
    List<Article> search(int categoryId, String state);
}
