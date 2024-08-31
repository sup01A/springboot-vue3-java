package com.example.springbootvue3.service.impl;

import com.example.springbootvue3.entity.Article;
import com.example.springbootvue3.entity.PageBean;
import com.example.springbootvue3.mapper.ArticleMapper;
import com.example.springbootvue3.service.ArticleService;
import com.example.springbootvue3.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<Article> getAll() {
        return articleMapper.getAll();
    }

    @Override
    public int add(Article article) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        int id = (int)map.get("id");
        article.setCreateUser(id);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        return articleMapper.add(article);
    }

    @Override
    public List<Article> list(int userId, Integer categoryId, String state) {

        return articleMapper.list(userId,categoryId,state);
    }

    @Override
    public int update(Article article) {
        return articleMapper.update(article);
    }

    @Override
    public int delete(int id) {
        return articleMapper.delete(id);
    }

    @Override
    public Article detail(int id) {
        return articleMapper.detail(id);
    }

    @Override
    public List<Article> search(int categoryId, String state) {
        return articleMapper.search(categoryId,state);
    }

    public PageBean<Article> listPage(int pageNum,int pageSiz,Integer categoryId, String state) {
        Map<String,Object> map =  ThreadLocalUtil.get();
        int id = (int)map.get("id");
        PageBean<Article> articlePageBean = new PageBean<>();
        PageHelper.startPage(pageNum,pageSiz);
        List<Article> list = list(id, categoryId, state);
        Page<Article> articlePage = (Page<Article>) list;
        articlePageBean.setTotal(articlePage.getTotal());
        articlePageBean.setItems(articlePage.getResult());
        return articlePageBean;
    }
}
