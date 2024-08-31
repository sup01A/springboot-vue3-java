package com.example.springbootvue3.service;

import com.example.springbootvue3.entity.Song;
import com.example.springbootvue3.mapper.SongMapper;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    private SongMapper songMapper;

    public SongService(SongMapper songMapper) {
        this.songMapper = songMapper;
    }
    public Song getSongById(int id){
        return songMapper.getSongById(id);
    }
}
