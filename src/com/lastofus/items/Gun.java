package com.lastofus.items;

import com.apps.util.Console;
import com.lastofus.player.Item;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Gun extends Item {

    private int charges;

    public Gun(int charges) {
        this.charges = charges;
    }

    @Override
    public void use(Player player) {
        Console.clear();
        if(charges > 0) {
            player.setAttack(player.getAttack() + 100);
            System.out.println("You used a gun and gained 100 attack");
            player.setHasGun(true);
            charges--;
        } else {
            System.out.println("You have no more charges");
        }
    }

    public void display() {
        try {
            String path = "backpackArt/Gun.txt";
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
        System.out.println("Gun: \n" +
                "[1] Equip it. This will increase your attack by 100. This will consume the item.\n" +
                "[2] Go back.");
        System.out.println("Your current health is: " + player1.getHealth());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}