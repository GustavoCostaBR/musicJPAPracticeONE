package com.allogica.musicJPAPractice.Model.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    public Album() {
    }

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    @OneToMany(mappedBy = "album")
    private List<Music> musics;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
