package com.example.springbootvue3.service;

import com.example.springbootvue3.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAll();
    int add(Article article);
    List<Article> list(int userId, Integer categoryId,String state);
    int update(Article article);
    int delete(int id);
    Article detail(int id);
    List<Article> search(int categoryId, String state);
}
