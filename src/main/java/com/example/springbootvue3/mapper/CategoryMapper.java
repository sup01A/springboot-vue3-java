package com.example.springbootvue3.mapper;

import com.example.springbootvue3.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category values (#{id},#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    int add(Category category);
    @Select("select * from category where create_user = #{id}")
    List<Category> list(int id);
    @Select("select * from category where id = #{id} ")
    Category detail(int id);
    @Update("update category set category_name = #{categoryName},category_alias = #{categoryAlias},update_time = now() where id = #{id}")
    int update(Category category);
    @Delete("delete from category where id = #{id}")
    int delete(int id);
}
