package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Battle extends Event {

    private final Zombie zombie = new Zombie();
    private int runCounter = 0;
    private int hideCounter = 0;
    private boolean quit = false;


    public Battle(Player currentPlayer) {
        super(currentPlayer);
        sceneList.add(new Scene("Battle", "1"));
        sceneList.add(new Scene("Battle", "2"));
        sceneList.add(new Scene("Battle", "3"));
        sceneList.add(new Scene("Battle", "4"));
        sceneList.add(new Scene("Battle", "5"));
        sceneList.add(new Scene("Battle", "6"));
        sceneList.add(new Scene("Battle", "7"));
    }

    @Override
    public void begin() {
        // while the player is still alive and the sceneList is not empty
        // play the first scene and prompt the user for input
        if (player.hasGun() && !quit) {
            branchGunLoop();
        }

        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();

            /*
             * Add art here
             */
            zombie.display();

            sceneList.get(0).begin();
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            if (zombie.getZHealth() <= 0) {
                break;
            }

            if (player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    System.out.println("Punching the zombie in the face did not work.");
                    appController.nextScene();
                    branchFightingLoop();
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    // hide
                    hideCounter++;
                    branchHidingLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if (player.hasGun()) {
                        branchGunLoop();
                    } else {
                        begin();
                    }
                    break;
            }
        }
    }

    private void branchGunLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && zombie.getZHealth() > 0 && !quit) {
            Console.clear();
            zombie.display();
            // attacked the zombie with a gun.

            sceneList.get(6).begin();
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    Console.clear();
                    zombie.setZHealth(zombie.getZHealth() - player.getAttack());
                    displayZombieDead();
                    System.out.println("You killed the zombie. It's hard to imagine the zombie was once a person.");
                    player.setHasGun(false);
                    quit = true;
                    appController.nextScene();
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchHidingLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    break;
            }
        }
    }

    // fighting branch
    private void branchFightingLoop() {
        if (player.hasGun() && !quit) {
            branchGunLoop();
        }
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {

            System.out.println("You lost 10 health.");
            appController.nextScene();
            Console.clear();
            // attacked the zombie with bare hands.
            displayZombieAngry();

            sceneList.get(1).begin();
            player.setHealth(player.getHealth() - 10);
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    System.out.println("Punching the zombie in the face did not work.");
                    appController.nextScene();
                    branchFightingLoop();
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    // hide
                    hideCounter++;
                    branchHidingLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if (player.hasGun()) {
                        branchGunLoop();
                    } else {
                        System.out.println("The zombie catches you off guard and scratches you!");
                        branchFightingLoop();
                    }
                    break;
            }
        }
    }


    // running branch
    private void branchRunningLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            if (runCounter >= 2 && runCounter < 5 && !quit) {
                displayExhausted();
                System.out.println("You are getting exhausted...");
                appController.nextScene();
            }
            if (runCounter >= 5 && runCounter <= 8 && !quit) {
                displayFightingStance();
                System.out.println("You can't run anymore! You have to fight! The zombie scratches you.");
                appController.nextScene();
                branchFightingLoop();
                return;
            }
            if (runCounter > 8 && !quit) {
                deathLoop();
            }
            Console.clear();

            displayZombieChase();

            // ran from zombie
            sceneList.get(5).begin();
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if (player.hasGun()) {
                        branchGunLoop();
                        return;
                    } else {
                        branchFightingLoop();
                    }
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchHidingLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if (player.hasGun()) {
                        branchGunLoop();
                    } else {
                        branchFightingLoop();
                    }
                    break;
            }
        }
    }

    // hiding branch
    private void branchHidingLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // hid from zombie

            if (hideCounter == 2 && !quit) {
                displayHeat();
                System.out.println("You hold your breathe, but the zombie can sense your body heat!");
                appController.nextScene();
            }
            if (hideCounter > 2 && hideCounter <= 5 && !quit) {
                displayFightingStance();
                System.out.println("The zombie is too close. You can't hide anymore. You have to fight! The zombie scratches you.");
                appController.nextScene();
                branchFightingLoop();
                break;
            }
            if (hideCounter > 5 && !quit) {
                deathLoop();
                break;
            }
            Console.clear();

            displayZombieChase();

            sceneList.get(4).begin();
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            if (player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if (player.hasGun()) {
                        branchGunLoop();
                        return;
                    } else {
                        player.setHealth(player.getHealth() - 10);
                        System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                        branchFightingLoop();
                    }
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchHidingLoop();
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

            player.setHealth(player.getHealth() - 30);
            sceneList.get(3).begin();
            System.out.println(ANSI_RED + "Your health: " + player.getHealth() + ANSI_RESET);
            if (player.getHealth() <= 0) {
                System.out.println("You died.");
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if (player.hasGun()) {
                        branchGunLoop();
                    } else {
                        player.setHealth(player.getHealth() - 10);
                        System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                        branchFightingLoop();
                    }
                    break;
                case 2:
                    runCounter++;
                    branchRunningLoop();
                    break;
                case 3:
                    hideCounter++;
                    branchHidingLoop();
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
        if (zombie.getZHealth() <= 0) {
            displayZombieDead();
            System.out.println("You killed the zombie. It's getting late and you need shelter. You go into a nearby forest");
            player.setAttack(player.getAttack() - 50);
        }
    }

    private void displayZombieAngry() {
        try {
            String path = "sceneArt/ZombieAngry.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displaySlash() {
        try {
            String path = "sceneArt/Slash.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayZombieChase() {
        try {
            String path = "sceneArt/ZombieChase.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayZombieDead() {
        try {
            String path = "sceneArt/ZombieDead.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayExhausted() {
        try {
            String path = "sceneArt/Exhausted.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayFightingStance() {
        try {
            String path = "sceneArt/FightingStance.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayHeat() {
        try {
            String path = "sceneArt/Heat.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}