package com.allogica.musicJPAPractice.Model.Services;

import com.allogica.musicJPAPractice.Model.Auxiliaries.ReceiveSpecificInteger;
import com.allogica.musicJPAPractice.Model.Entities.Album;
import com.allogica.musicJPAPractice.Model.Entities.Artist;
import com.allogica.musicJPAPractice.Model.Entities.Music;
import com.allogica.musicJPAPractice.Model.Repositories.AlbumRepository;
import com.allogica.musicJPAPractice.Model.Repositories.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class AlbumService {
    private final Scanner keyboard = new Scanner(System.in);
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistService artistService;

    @Transactional
    public long registerAlbum() {
        System.out.println("Register album...");
        System.out.println("Type in the title of the album: ");
        var name = keyboard.nextLine();
        Optional<Artist> optArtist = artistService.getArtistByFragmentNameAndValidate();
        if (optArtist.isPresent()) {
            Artist artist = optArtist.get();
            Album album = new Album(name, artist);
            albumRepository.save(album);
            System.out.println("Album registered successfully!");
            return album.getId();
        } else {
            System.out.println("Problem getting album. \nReturning to the main menu...");
            return -1;
        }
    }

    //    TODO: quase ctz que vai dar ruim aqui por acessar a lista de artistas sem ser transactional

    Optional<Album> getAlbumByFragmentNameAndValidate() {
        List<Album> albums = getAlbumByFragmentName();
        return validateIfAlbumInListIfNotRegister(albums);
    }

    private List<Album> getAlbumByFragmentName() {
        List<Album> albums;
        System.out.println("Type in a fragment of the album title: ");
        var albumTitle = keyboard.nextLine();
        albums = albumRepository.findByTitleContainingIgnoreCase(albumTitle);
        return albums;
    }

    private Optional<Album> validateIfAlbumInListIfNotRegister(List<Album> albums) {
        if (albums.isEmpty()) {
            return handleEmptyAlbumList();
        }
        return handleNonEmptyAlbumList(albums);
    }

    private Optional<Album> handleEmptyAlbumList() {
        System.out.println("No album found with the given title.\n Choose among the options: \n 1 - Try again \n 2 - Register a new album \n 3 - Continue without album");
        int option = ReceiveSpecificInteger.receiveInteger(1, 3);
        return switch (option) {
            case 1 -> getAlbumByFragmentNameAndValidate();
            case 2 -> getAlbumById(registerAlbum());
            default -> Optional.empty();
        };
    }

    private Optional<Album> handleNonEmptyAlbumList(List<Album> albums) {
        albums.forEach(System.out::println);
        System.out.println("Is the album you were looking for in the list? \n 1 - Yes \n 2 - No");
        int option = ReceiveSpecificInteger.receiveInteger(1, 2);
        if (option == 1) {
            return selectAlbumFromList(albums);
        } else {
            return getAlbumById(registerAlbum());
        }
    }

    private Optional<Album> selectAlbumFromList(List<Album> albums) {
        System.out.println("Type in the id of the album: ");
        Long albumId = ReceiveSpecificInteger.receiveLong(albums.stream().map(Album::getId).toList());
        return getAlbumById(albumId);
    }

    Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }
}
