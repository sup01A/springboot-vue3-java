package com.example.springbootvue3.controller;

import com.example.springbootvue3.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin("http://localhost:5173")
public class FileUploadController {
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws IOException {
        file.transferTo(new File("/home/oushubo/Videos/movie/04_综合案例资料/uploadFile/" +
                file.getOriginalFilename()));
        return Result.success("url访问地址...");
    }
}
