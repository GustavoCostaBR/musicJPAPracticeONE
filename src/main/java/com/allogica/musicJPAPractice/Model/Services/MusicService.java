package com.allogica.musicJPAPractice.Model.Services;

import com.allogica.musicJPAPractice.Model.Auxiliaries.ReceiveSpecificInteger;
import com.allogica.musicJPAPractice.Model.Entities.Album;
import com.allogica.musicJPAPractice.Model.Entities.Artist;
import com.allogica.musicJPAPractice.Model.Entities.Music;
import com.allogica.musicJPAPractice.Model.Repositories.MusicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class MusicService {

    private final Scanner keyboard = new Scanner(System.in);

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MusicRepository musicRepository;

    @Transactional
    public void registerMusic() {
        Long artistId;
        Music music;
        System.out.println("Register music...");
        System.out.println("Type in the name of the music: ");
        var name = keyboard.nextLine();
        Optional<Album> optAlbum = albumService.getAlbumByFragmentNameAndValidate();
        if (optAlbum.isPresent()){
            Album album = optAlbum.get();
            music = new Music(name, album.getArtist(), album);
            musicRepository.save(music);
            System.out.println("Music registered successfully!");
            return;
        }
        else {
            System.out.println("Continuing without album...");
            Optional<Artist> optArtist = artistService.getArtistByFragmentNameAndValidate();
            if (optArtist.isPresent()){
                Artist artist = optArtist.get();
                music = new Music(name, artist);
                musicRepository.save(music);
                System.out.println("Music registered successfully!");
                return;
            }
            else {
                System.out.println("Returning to the main menu...");
                return;
            }
        }
    }
}
