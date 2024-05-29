package com.allogica.musicJPAPractice.Model.Repositories;

import com.allogica.musicJPAPractice.Model.Entities.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByNameContainingIgnoreCase(String title);
    List<Music> findByArtistNameContainingIgnoreCase(String name);
    List<Music> findByArtistId(Long id);
    
}
