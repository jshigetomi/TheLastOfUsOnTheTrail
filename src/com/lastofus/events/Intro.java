package com.lastofus.events;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Intro extends Event {
    private Prompter prompter = new Prompter(new Scanner(System.in));
    private boolean quit = false;
    private boolean quitGame = false;

    public Intro(Player player) {
        super(player);
        sceneList.add(new Scene("Intro", "1"));
        sceneList.add(new Scene("Intro", "2"));
    }

    @Override
    public void begin() {
        Console.clear();
        displayCity();
        sceneList.get(0).begin();
        System.out.println();
        appController.nextScene();

        Console.clear();
        displayJoe();
        sceneList.get(1).begin();
        System.out.println();
        appController.nextScene();
    }

    private void displayCity() {
        try {
            String path = "sceneArt/City.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayJoe() {
        try {
            String path = "sceneArt/Joe.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
