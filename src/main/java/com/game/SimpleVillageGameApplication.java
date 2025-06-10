package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleVillageGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleVillageGameApplication.class, args);
        System.out.println("🏰 Prosta Gra Osadnicza uruchomiona!");
    }
}