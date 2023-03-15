package com.lastofus.events;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.lastofus.player.Player;

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
        sceneList.get(0).begin();
        System.out.println();
        appController.nextScene();

        Console.clear();
        sceneList.get(1).begin();
        System.out.println();
        appController.nextScene();
    }
}
