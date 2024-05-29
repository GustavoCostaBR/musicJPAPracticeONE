package com.allogica.musicJPAPractice.Model.Auxiliaries;

import com.allogica.musicJPAPractice.Model.Entities.Music;

import java.util.List;

public class CustomPrinter {

    public static void printMusicList(List<Music> musics) {
        musics.forEach(System.out::println);
    }
}
