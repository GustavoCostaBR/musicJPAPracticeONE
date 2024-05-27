package com.allogica.musicJPAPractice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicJpaPracticeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MusicJpaPracticeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
	}
}
