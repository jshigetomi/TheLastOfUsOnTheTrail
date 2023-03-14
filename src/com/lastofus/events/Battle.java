package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Battle extends Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();
    private final Zombie zombie = new Zombie();
    private int runCounter = 0;
    private int hideCounter = 0;
    private boolean quit = false;

    public Battle(Player currentPlayer) {
        super();
        this.player = currentPlayer;
        sceneList.add(new Scene("Battle", "1"));
        sceneList.add(new Scene("Battle","2"));
        sceneList.add(new Scene("Battle","3"));
        sceneList.add(new Scene("Battle","4"));
        sceneList.add(new Scene("Battle","5"));
        sceneList.add(new Scene("Battle","6"));
        sceneList.add(new Scene("Battle","7"));
    }

    @Override
    public void begin() {
        // while the player is still alive and the sceneList is not empty
        // play the first scene and prompt the user for input

        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();

            /*
             * Add art here
             */
            zombie.display();

            sceneList.get(0).begin();
            System.out.println("Your health: " + player.getHealth());
            if(zombie.getZHealth() <= 0 ) {
                break;
            }

            if(player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    appController.nextScene();
                    branchOneLoop();
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    // hide
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if(player.hasGun()) {
                        branchGunLoop();
                    }
                    else {
                        begin();
                    }
                    break;
            }
        }
        end();
        appController.nextScene();
    }

    private void branchGunLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && zombie.getZHealth() > 0 && !quit) {
            Console.clear();
            zombie.display();
            // attacked the zombie with a gun.

            sceneList.get(6).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    zombie.setZHealth(zombie.getZHealth() - 1000);
                    branchGunLoop();
                    quit = true;
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    break;
            }
        }
    }

    // fighting branch
    private void branchOneLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // attacked the zombie with bare hands.
            displayZombieAngry();

            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    appController.nextScene();
                    branchOneLoop();
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    // hide
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if(player.hasGun()) {
                        branchGunLoop();
                    }
                    else {
                        branchOneLoop();
                    }
                    break;
            }
        }
    }


    // running branch
    private void branchTwoLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            if(runCounter >= 2 && runCounter < 5) {
                System.out.println("You are getting exhausted...");
                appController.nextScene();
            }
            if(runCounter >= 5 && runCounter <= 8) {
                System.out.println("You can't run anymore! You have to fight!");
                player.setHealth(player.getHealth()-10);
                appController.nextScene();
                branchOneLoop();
                return;
            }
            if(runCounter > 8) {
                deathLoop();
            }
            Console.clear();

            displayZombieChase();

            // ran from zombie
            sceneList.get(5).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    branchOneLoop();
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if(player.hasGun()) {
                        branchGunLoop();
                    }
                    else {
                        branchOneLoop();
                    }
                    break;
            }
        }
    }

    // hiding branch
    private void branchThreeLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // hid from zombie

            if(hideCounter == 2) {
                System.out.println("You hold your breathe, but the zombie can sense your body heat!");
                appController.nextScene();
            }
            if(hideCounter > 2 && hideCounter <= 5) {
                System.out.println("The zombie is too close. You can't hide anymore. You have to fight!");
                player.setHealth(player.getHealth()-10);
                appController.nextScene();
                branchOneLoop();
            }
            if(hideCounter > 5) {
                deathLoop();
            }
            Console.clear();

            displayZombieChase();

            sceneList.get(4).begin();
            System.out.println("Your health: " + player.getHealth());
            if(player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    branchOneLoop();
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    // scream
                    player.setHealth(player.getHealth() - 30);
                    System.out.println("You were slashed by the zombie. You lost 30 health.");
                    appController.nextScene();
                    deathLoop();
                    break;
            }
        }
    }

    private void deathLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // slashed by zombie
            displaySlash();

            player.setHealth(player.getHealth()-30);
            sceneList.get(3).begin();
            System.out.println("Your health: " + player.getHealth());
            if(player.getHealth() <= 0) {
                System.out.println("You died.");
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    branchOneLoop();
                    break;
                case 2:
                    runCounter++;
                    branchTwoLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchThreeLoop();
                    break;
                case 4:
                    player.setHealth(player.getHealth() - 30);
                    System.out.println("You were slashed by the zombie. You lost 30 health.");
                    appController.nextScene();
                    deathLoop();
                    break;
            }
        }
    }

    @Override
    public void end() {
        Console.clear();
        if(zombie.getZHealth() <= 0 ) {
            displayZombieDead();
            System.out.println("You killed the zombie. It's getting late and you need shelter. You go into a nearby forest");
            player.setAttack(player.getAttack() - 50);
        }
    }

    private void displayZombieAngry () {
        try {
            String path = "sceneArt/ZombieAngry.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displaySlash() {
        try {
            String path = "sceneArt/Slash.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayZombieChase() {
        try {
            String path = "sceneArt/ZombieChase.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void displayZombieDead() {
        try {
            String path = "sceneArt/ZombieDead.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}