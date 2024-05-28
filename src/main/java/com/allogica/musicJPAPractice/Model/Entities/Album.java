package com.allogica.musicJPAPractice.Model.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Album {
    public Long getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Album id: " + id + "; " + "Title: " + title + "; " + "Artist: " + this.artist.getName() + ".";
    }

    @OneToMany(mappedBy = "album")
    private List<Music> musics;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }
}
