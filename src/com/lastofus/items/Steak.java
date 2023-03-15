package com.lastofus.items;

import com.apps.util.Console;
import com.lastofus.player.Item;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Steak extends Item {

    private int charges;

    public Steak(int charges) {
        this.charges = charges;
    }

    public void use(Player player) {
        Console.clear();
        if(charges > 0) {
            player.setHealth(player.getHealth() + 30);
            System.out.println("You used a steak and gained 30 health");
            charges--;
        } else {
            System.out.println("You have no more charges");
        }
    }

    public void display() {
        try {
            String path = "backpackArt/Steak.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void menu(Player player1) {
        display();
        System.out.println("Steak: \n" +
                "[1] Eat it to restore 30 health. This will consume the item.\n" +
                "[2] Go back.");
        System.out.println("Your current health is: " + player1.getHealth());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}