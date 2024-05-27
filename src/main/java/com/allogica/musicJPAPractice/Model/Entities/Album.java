package com.allogica.musicJPAPractice.Model.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Album() {
    }

    public Album(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
    }

    @OneToMany(mappedBy = "album")
    private List<Music> musics;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;
}
