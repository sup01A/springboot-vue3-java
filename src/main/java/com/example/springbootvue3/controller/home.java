package com.example.springbootvue3.controller;

import com.example.springbootvue3.entity.Song;
import com.example.springbootvue3.service.SongService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:5173")
public class home {
    private SongService songService;

    public home(SongService songService) {
        this.songService = songService;
    }
    @RequestMapping("/")
    public String home(){
        return "Hello World!!!";
    }
    @RequestMapping("/song/{id}")
    public Song getSongById(@PathVariable("id") int id){
        return songService.getSongById(id);
    }
}
