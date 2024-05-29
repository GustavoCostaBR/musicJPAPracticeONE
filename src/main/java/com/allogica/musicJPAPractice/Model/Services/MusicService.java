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
        System.out.println("Register music...");
        String name = getMusicNameFromUser();
        if (userWantsToRegisterWithAlbum()) {
            handleMusicRegistrationWithAlbum(name);
        } else {
            handleMusicRegistrationWithoutAlbum(name);
        }
    }

    private String getMusicNameFromUser() {
        System.out.println("Type in the name of the music: ");
        return keyboard.nextLine();
    }

    private boolean userWantsToRegisterWithAlbum() {
        System.out.println("Do you want to register this music with an album? \n 1 - Yes \n 2 - No");
        int option = ReceiveSpecificInteger.receiveInteger(1, 2);
        return option == 1;
    }

    private void handleMusicRegistrationWithAlbum(String name) {
        Optional<Album> optAlbum = albumService.getAlbumByFragmentNameAndValidate();
        if (optAlbum.isPresent()) {
            Album album = optAlbum.get();
            saveMusic(name, album.getArtist(), album);
        } else {
            System.out.println("Continuing without album...");
            registerMusicWithoutAlbum(name);
        }
    }

    private void handleMusicRegistrationWithoutAlbum(String name) {
        System.out.println("Continuing without album...");
        registerMusicWithoutAlbum(name);
    }

    private void registerMusicWithoutAlbum(String name) {
        Optional<Artist> optArtist = artistService.getArtistByFragmentNameAndValidate();
        if (optArtist.isPresent()) {
            Artist artist = optArtist.get();
            saveMusic(name, artist);
        } else {
            System.out.println("Returning to the main menu...");
        }
    }

    private void saveMusic(String name, Artist artist) {
        Music music = new Music(name, artist);
        musicRepository.save(music);
        System.out.println("Music registered successfully!");
    }

    private void saveMusic(String name, Artist artist, Album album) {
        Music music = new Music(name, artist, album);
        musicRepository.save(music);
        System.out.println("Music registered successfully!");
    }

    public void registerAlbumForMusic(Music music) {
        int option = getUserOptionForAlbumRegistration();
        if (option == 2) {
            handleExistingAlbumSearch(music);
        } else {
            handleNewAlbumRegistration(music);
        }
    }

    private int getUserOptionForAlbumRegistration() {
        System.out.println("Do you want to register a new album or search for one already registered? \n 1 - Register new album \n 2 - Search for one already registered");
        return ReceiveSpecificInteger.receiveInteger(1, 2);
    }

    private void handleExistingAlbumSearch(Music music) {
        Optional<Album> optAlbum = albumService.getAlbumByFragmentNameAndValidate();
        if (optAlbum.isPresent()) {
            Album album = optAlbum.get();
            setAlbumIfArtistMatches(album, music);
        } else {
            System.out.println("Returning to the main menu...");
            return;
        }
    }

    private void handleNewAlbumRegistration(Music music) {
        long albumId = albumService.registerAlbum();
        if (albumId != -1) {
            Album album = albumService.getAlbumById(albumId).orElse(null);
            if (album != null) {
                setAlbumIfArtistMatches(album, music);
            } else {
                System.out.println("Returning to the main menu...");
                return;
            }
        } else {
            System.out.println("Returning to the main menu...");
            return;
        }
    }

    private void setAlbumIfArtistMatches(Album album, Music music) {
        if (album.getArtist().getId().equals(music.getArtist().getId())) {
            music.setAlbum(album);
            musicRepository.save(music);
        } else {
            handleArtistMismatch();
        }
    }

    private void handleArtistMismatch() {
        System.out.println("The album artist is different from the music artist.");
        System.out.println("Returning to the main menu...");
        return;
    }

}
