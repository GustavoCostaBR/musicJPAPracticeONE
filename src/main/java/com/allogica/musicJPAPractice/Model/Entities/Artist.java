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

    @Enumerated(EnumType.STRING)
    private ArtistType type;

    @OneToMany(mappedBy = "artist")
    private List<Music> musics;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;
}
