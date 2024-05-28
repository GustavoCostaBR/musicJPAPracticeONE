package com.allogica.musicJPAPractice.Model.Services;

import com.allogica.musicJPAPractice.Model.Auxiliaries.ArtistType;
import com.allogica.musicJPAPractice.Model.Auxiliaries.ReceiveSpecificInteger;
import com.allogica.musicJPAPractice.Model.Entities.Artist;
import com.allogica.musicJPAPractice.Model.Repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ArtistService {

    private final Scanner keyboard = new Scanner(System.in);
    @Autowired
    ArtistRepository artistRepository;


    public void registerArtist() {
        System.out.println("Register artist...");
        System.out.println("Type in the name of the artist: ");
        var name = keyboard.nextLine();
        System.out.println("Type in the type of the artist (solo, duo, band): ");
        var type = keyboard.nextLine();
        ArtistType artistType = ArtistType.valueOf(type.toUpperCase());
        Artist artist = new Artist(name, artistType);
        artistRepository.save(artist);
        System.out.println("Artist registered successfully!");
    }

    public void registerMusic() {
        System.out.println("Register music...");
        System.out.println("Type in the name of the music: ");
        var name = keyboard.nextLine();
        Optional<List<Artist>> artists = getArtistByFragmentName();
        if (artists.isEmpty()) {
            System.out.println("Returning to the main menu...");
            return;
        } else {
            artists.get().forEach(System.out::println);
            System.out.println("Type in the id of the artist: ");
            var artistId = ReceiveSpecificInteger.receiveLong(artists.get().stream().map(Artist::getId).toList());
        }



//        System.out.println("Type in a fragment of the album name: ");
//        var albumName = keyboard.nextLine();


    }

    private Optional<List<Artist>> getArtistByFragmentName() {
        List<Artist> artists;
        Integer option = 1;
        do {
            System.out.println("Type in a fragment of the artist name: ");
            var artistName = keyboard.nextLine();
            artists = artistRepository.findByNameContainingIgnoreCase(artistName);
            if (artists.isEmpty()) {
                System.out.println("No artist found with the given name.\n Choose among the options: \n 1 - Try again \n 2 - Register a new artist \n 3 - Exit to main menu");
                option = ReceiveSpecificInteger.receiveInteger(1, 3);
                if (option == 2) {
                    registerArtist();
                } else if (option == 3) {
                    return Optional.empty();
                }
            }
        } while (artists.isEmpty() && option <= 2);
        return Optional.of(artists);
    }


}
