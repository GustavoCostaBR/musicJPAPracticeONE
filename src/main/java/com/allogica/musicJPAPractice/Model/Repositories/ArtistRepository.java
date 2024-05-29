package com.allogica.musicJPAPractice.Model.Repositories;

import com.allogica.musicJPAPractice.Model.Entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long>{

    List<Artist> findByNameContainingIgnoreCase(String name);
}
