package com.allogica.musicJPAPractice.Model.Services;

import com.allogica.musicJPAPractice.Model.Auxiliaries.ArtistType;
import com.allogica.musicJPAPractice.Model.Auxiliaries.ReceiveSpecificInteger;
import com.allogica.musicJPAPractice.Model.Entities.Album;
import com.allogica.musicJPAPractice.Model.Entities.Artist;
import com.allogica.musicJPAPractice.Model.Entities.Music;
import com.allogica.musicJPAPractice.Model.Repositories.ArtistRepository;
import com.allogica.musicJPAPractice.Model.Repositories.MusicRepository;
import jakarta.transaction.Transactional;
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


    @Transactional
    public Long registerArtist() {
        System.out.println("Register artist...");
        System.out.println("Type in the name of the artist: ");
        var name = keyboard.nextLine();
        System.out.println("Type in the type of the artist (solo, duo, band): ");
        var type = keyboard.nextLine();
        ArtistType artistType = ArtistType.valueOf(type.toUpperCase());
        Artist artist = new Artist(name, artistType);
        artistRepository.save(artist);
        System.out.println("Artist registered successfully!");
        return artist.getId();
    }

    //    TODO: quase ctz que vai dar ruim aqui por acessar a lista de artistas sem ser transactional
    Optional<Artist> getArtistByFragmentNameAndValidate() {
        List<Artist> artists = getArtistByFragmentName();
        return validateIfArtistInListIfNotRegister(artists);
    }

    private List<Artist> getArtistByFragmentName() {
        List<Artist> artists;
        System.out.println("Type in a fragment of the artist name: ");
        var artistName = keyboard.nextLine();
        artists = artistRepository.findByNameContainingIgnoreCase(artistName);
        return artists;
    }

    private Optional<Artist> validateIfArtistInListIfNotRegister(List<Artist> artists) {
        if (artists.isEmpty()) {
            return handleEmptyArtistList();
        }
        return handleNonEmptyArtistList(artists);
    }

    private Optional<Artist> handleEmptyArtistList() {
        System.out.println("No artist found with the given name.\n Choose among the options: \n 1 - Try again \n 2 - Register a new artist \n 3 - Exit to main menu");
        int option = ReceiveSpecificInteger.receiveInteger(1, 3);
        return switch (option) {
            case 1 -> getArtistByFragmentNameAndValidate();
            case 2 -> getArtistById(registerArtist());
            default -> Optional.empty();
        };
    }

    private Optional<Artist> handleNonEmptyArtistList(List<Artist> artists) {
        artists.forEach(System.out::println);
        System.out.println("Is the artist you were looking for in the list? \n 1 - Yes \n 2 - No");
        int option = ReceiveSpecificInteger.receiveInteger(1, 2);
        if (option == 1) {
            return selectArtistFromList(artists);
        } else {
            return getArtistById(registerArtist());
        }
    }

    private Optional<Artist> selectArtistFromList(List<Artist> artists) {
        System.out.println("Type in the id of the artist: ");
        Long artistId = ReceiveSpecificInteger.receiveLong(artists.stream().map(Artist::getId).toList());
        return getArtistById(artistId);
    }

    private Optional<Artist> getArtistById(Long id) {
        return artistRepository.findById(id);
    }


}
