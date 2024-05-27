package com.allogica.musicJPAPractice.Model.Repositories;

import com.allogica.musicJPAPractice.Model.Entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
}
