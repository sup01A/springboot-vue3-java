package com.example.springbootvue3.controller;

import com.example.springbootvue3.entity.Category;
import com.example.springbootvue3.entity.Result;
import com.example.springbootvue3.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    /*
    添加分类
     */
    @PostMapping()
    public <T>Result<T>  add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success();
    }
    /*
    获取所有分类
     */
    @GetMapping()
    public Result<List<Category>> list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
    /*
    获取分类详情
     */
    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam int id){
        Category detail = categoryService.detail(id);
        return Result.success(detail);
    }
    /*
    更新文章分类信息
     */
    @PutMapping
    public <T>Result<T>  update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }
    /*
    删除文章类别信息
     */
    @DeleteMapping
    public <T>Result<T>  delete(@RequestParam int id){
        categoryService.delete(id);
        return Result.success();
    }
}


