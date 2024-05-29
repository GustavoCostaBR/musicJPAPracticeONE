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

    public Artist getArtist() {
        return artist;
    }

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @Override
    public String toString() {
        return "Music id: " + id +
                "; Name: " + name + '\'' +
                "; Artist: " + artist +
                "; Album: " + album +
                ".";
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

}
