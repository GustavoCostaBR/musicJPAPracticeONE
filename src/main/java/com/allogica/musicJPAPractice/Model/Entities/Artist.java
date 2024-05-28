package com.allogica.musicJPAPractice.Model.Entities;

import com.allogica.musicJPAPractice.Model.Auxiliaries.ArtistType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Artist() {}

    public Artist(String name, ArtistType type) {
        this.name = name;
        this.type = type;
    }

    private String name;

    public Long getId() {
        return id;
    }

    @Enumerated(EnumType.STRING)
    private ArtistType type;

    @Override
    public String toString() {
        return "Artist id: " + id + "; " + "Name: " + name + "; " + "Type: " + type + ".";
    }

    @OneToMany(mappedBy = "artist")
    private List<Music> musics;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;
}
