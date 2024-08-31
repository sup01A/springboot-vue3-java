package com.example.springbootvue3.entity;

import lombok.Data;

@Data
public class Song {
    int songId;			// 歌曲ID
    int singerId;		// 所属歌手ID
    int albumId;		// 所属专辑ID
    String songTitle;	// 歌曲名
    int songPlaytimes;	// 歌曲播放次数
    int songDldtimes;	// 歌曲下载次数
    String songFile;	// 歌曲文件名
    String songLyric;
}
