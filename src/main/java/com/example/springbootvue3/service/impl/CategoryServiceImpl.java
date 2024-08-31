package com.example.springbootvue3.service.impl;

import com.example.springbootvue3.entity.Category;
import com.example.springbootvue3.mapper.CategoryMapper;
import com.example.springbootvue3.service.CategoryService;
import com.example.springbootvue3.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public int add(Category category) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        int id = (int)map.get("id");
        category.setCreateUser(id);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map =  ThreadLocalUtil.get();
        int userid = (int)map.get("id");
        return categoryMapper.list(userid);
    }

    @Override
    public Category detail(int id) {
        return categoryMapper.detail(id);
    }

    @Override
    public int update(Category category) {
        return categoryMapper.update(category);
    }

    @Override
    public int delete(int id) {
        return categoryMapper.delete(id);
    }
}
