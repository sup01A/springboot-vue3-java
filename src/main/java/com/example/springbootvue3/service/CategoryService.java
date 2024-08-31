package com.example.springbootvue3.service;

import com.example.springbootvue3.entity.Category;

import java.util.List;


public interface CategoryService {
    int add(Category category);
    List<Category> list();
    Category detail(int id);
    int update(Category category);
    int delete(int id);
}
