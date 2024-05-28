package com.allogica.musicJPAPractice;

import com.allogica.musicJPAPractice.Menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicJpaPracticeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MusicJpaPracticeApplication.class, args);
	}

	@Autowired
	Menu menu;
	@Override
	public void run(String... args) throws Exception {
		menu.showMenu();
	}
}
