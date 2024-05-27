package com.allogica.musicJPAPractice.Model.Entities;

import jakarta.persistence.*;

@Entity
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Music() {}

    public Music(String name, Artist artist) {
        this.name = name;
        this.artist = artist;
    }
    public Music(String name, Artist artist, Album album) {
        this.name = name;
        this.artist = artist;
        this.album = album;
    }

    private String name;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

}
