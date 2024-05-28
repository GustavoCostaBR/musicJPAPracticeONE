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
    public void registerAlbum() {
        System.out.println("Register album...");
        System.out.println("Type in the title of the album: ");
        var name = keyboard.nextLine();
        Optional<Artist> optArtist = artistService.getArtistByFragmentNameAndValidate();
        if (optArtist.isPresent()) {
            Artist artist = optArtist.get();
            Album album = new Album(name, artist);
            albumRepository.save(album);
            System.out.println("Album registered successfully!");
            return;
        } else {
            System.out.println("Returning to the main menu...");
            return;
        }
    }

    //    TODO: quase ctz que vai dar ruim aqui por acessar a lista de artistas sem ser transactional
    Optional<Album> getAlbumByFragmentNameAndValidate() {
        List<Album> albums = getAlbumByFragmentName();
        if (albums.isEmpty()) {
            return Optional.empty();
        }
        albums.forEach(System.out::println);
        System.out.println("Type in the id of the album: ");
        var albumId = ReceiveSpecificInteger.receiveLong(albums.stream().map(Album::getId).toList());
        return getAlbumById(albumId);
    }


    List<Album> getAlbumByFragmentName() {
        List<Album> albums;
        Integer option = 1;
        do {
            System.out.println("Type in a fragment of the album name: ");
            var albumName = keyboard.nextLine();
            albums = albumRepository.findByTitleContainingIgnoreCase(albumName);
            if (albums.isEmpty()) {
                System.out.println("No album found with the given name.\n Choose among the options: \n 1 - Try again \n 2 - Register a new album \n 3 - Exit and continue without album");
                option = ReceiveSpecificInteger.receiveInteger(1, 3);
                if (option == 2) {
                    registerAlbum();
                } else if (option == 3) {
                    return albums;
                }
            }
        } while (albums.isEmpty() && option <= 2);
        return albums;
    }

    Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }
}
