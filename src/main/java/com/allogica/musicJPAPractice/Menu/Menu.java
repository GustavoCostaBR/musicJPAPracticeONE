package com.allogica.musicJPAPractice.Menu;

import com.allogica.musicJPAPractice.Model.Entities.Artist;
import com.allogica.musicJPAPractice.Model.Repositories.ArtistRepository;
import com.allogica.musicJPAPractice.Model.Services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Menu {

    @Autowired
    private ArtistService artistService;
    private final Scanner keyboard = new Scanner(System.in);

    public void showMenu() {
        var option = -1;
        while(option != 0) {
            var menu = """
                    1 - Register artist
                    2 - Register music
                    3 - List musics
                    4 - Find musics by artist
                    5 - Search artist data
                    9 - Exit
                    """;

            System.out.println(menu);
            option = keyboard.nextInt();
            keyboard.nextLine();

            switch (option) {
                case 1:
                    artistService.registerArtist();
                    break;
                case 2:
                    artistService.registerMusic();
                    break;
//                case 3:
//                    listarMusicas();
//                    break;
//                case 4:
//                    buscarMusicasPorArtista();
//                    break;
//                case 5:
//                    pesquisarDadosArtista();
//                    break;
                case 9:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }


//
//    public void cadastrarMusica() {
//        System.out.println("Cadastrando música...");
//        System.out.println("Digite o nome da música: ");
//        var nome = keyboard.nextLine();
//        System.out.println("Digite o nome do artista: ");
//        var artista = keyboard.nextLine();
//        System.out.println("Música cadastrada com sucesso!");
//    }
//
//    public void listarMusicas() {
//        System.out.println("Listando músicas...");
//        System.out.println("Músicas listadas com sucesso!");
//    }
//
//    public void buscarMusicasPorArtista() {
//        System.out.println("Buscando músicas por artista...");
//        System.out.println("Digite o nome do artista: ");
//        var artista = leitura.nextLine();
//        System.out.println("Músicas encontradas com sucesso!");
//    }
//
//    public void pesquisarDadosArtista() {
}
