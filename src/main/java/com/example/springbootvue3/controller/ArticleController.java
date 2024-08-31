package com.example.springbootvue3.controller;

import com.example.springbootvue3.entity.Article;
import com.example.springbootvue3.entity.PageBean;
import com.example.springbootvue3.entity.Result;
import com.example.springbootvue3.service.impl.ArticleServiceImpl;
import com.example.springbootvue3.validation.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/article")
@Validated
@CrossOrigin("http://localhost:5173")
public class ArticleController {
    private ArticleServiceImpl articleService;

    public ArticleController(ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }
    /*
    添加文章
     */
    @PostMapping
    public <T>Result<T> add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success();
    }
    /*
    分页获取文章
     */
    @GetMapping
    public Result<PageBean<Article>> list(int pageNum,int pageSize,@RequestParam(required = false) Integer categoryId,@RequestParam(required = false) String state){
        PageBean<Article> articlePageBean = articleService.listPage(pageNum, pageSize, categoryId, state);
        return Result.success(articlePageBean);
    }
    /*
    更新文章
     */
    @PutMapping
    public <T>Result<T>  update(@RequestBody @Validated(Article.update.class) Article article){
        articleService.update(article);
        return Result.success();
    }
    /*
    删除文章
     */
    @DeleteMapping
    public <T>Result<T>  delete(@RequestParam(required = true) int id){
        articleService.delete(id);
        return Result.success();
    }
    /*
    获取文章详情
     */
    @GetMapping("/detail")
    public Result<Article> detail(int id){
        Article detail = articleService.detail(id);
        return Result.success(detail);
    }
    @GetMapping("/search")
    public Result<List<Article>> search(@RequestParam @NotNull int categoryId,@RequestParam @State String state){
        List<Article> search = articleService.search(categoryId, state);
        return Result.success(search);
    }
}
