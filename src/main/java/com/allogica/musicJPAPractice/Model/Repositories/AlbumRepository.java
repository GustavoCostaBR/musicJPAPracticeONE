package com.allogica.musicJPAPractice.Model.Repositories;

import com.allogica.musicJPAPractice.Model.Entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByTitleContainingIgnoreCase(String title);
    List<Album> findByArtistNameContainingIgnoreCase(String name);
    
}
