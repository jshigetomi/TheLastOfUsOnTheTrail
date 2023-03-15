package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.items.Hatchet;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class Forest extends Event{
    private final Zombie zombie = new Zombie();
    private boolean quit = false;
    private int battleCounter = 0;
    private int runCounter = 0;



    public Forest(Player currentPlayer) {
        super(currentPlayer);
        this.player = currentPlayer;
        sceneList.add(new Scene("Forest", "1"));
        sceneList.add(new Scene("Forest", "2"));
        sceneList.add(new Scene("Forest","3"));
        sceneList.add(new Scene("Forest","4"));
        sceneList.add(new Scene("Forest", "5"));
    }

    public void begin() {

        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            /*
             * Add art here
             */
            displayForest();

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
                    player.setHealth(player.getHealth() + 20);
                    System.out.println("Whew...A spot to rest for the night. You earned 20 health.");
                    appController.nextScene();
                    branchCampLoop();
                    break;
                case 2:
                    //build a trap
                    System.out.println("You caught a zombie. Time to kill it.");
                    appController.nextScene();
                    trapLoop();
                    player.setAttack(75); //FIX
                    break;
                case 3:
                    // keep running into the abyss
                    Battle battle = new Battle(player);
                    battle.begin(); //keep running
                    break;
                case 4:
                    //hide in a tree
                    System.out.println("You found a hatchet!");
                    appController.nextScene();
                    Hatchet hatchet = new Hatchet(2);
                    hatchet.display();
                    branchCreeperLoop();
                    break;
            }
        }
    }

    private void branchHatchetLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && zombie.getZHealth() > 0 && !quit) {
            Console.clear();
            zombie.display();
            // attacked the zombie with a gun.

            sceneList.get(6).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-2]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    Console.clear();
                    zombie.setZHealth(zombie.getZHealth() - player.getAttack());
                    displayZombieDead();
                    System.out.println("You killed the zombie. It's hard to imagine the zombie was once a person.");
                    player.setHasHatchet(false);
                    quit = true;
                    appController.nextScene();
                    break;
                case 2:
                    runCounter++;
                    branchLightLoop();
                    break;
            }
        }
    }

    private void trapLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            sceneList.get(4).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    System.out.println("The zombie's head explodes and its body twitches");
                    System.out.println("Justice has been served!");
                    appController.nextScene();
                    begin();
                    break;
                case 2:
                    System.out.println("The zombie laughs");
                    System.out.println("Your hand is bleeding from the impact. You lost 10 health");
                    player.setHealth(player.getHealth()-10);
                    appController.nextScene();
                    trapLoop();
                    break;
                case 3:
                    System.out.println("The zombie regains its previous consciousness as person");
                    System.out.println("The zombie gives you a warm embrace!");
                    player.setFriend(true);
                    quit = true;
                    begin();
                    break;
                case 4:
                    begin();
                    break;
            }
        }
    }

    // Set up camp
    private void branchCampLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // scene 2
            displayShelter();

            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    //investigate
                    if(battleCounter == 0) {
                        battleCounter++;
                        player.setHealth(player.getHealth() - 10);
                        System.out.println("You have ran into a zombie");
                        Battle battle = new Battle(player);
                        battle.begin();
                    }
                    else {
                        System.out.println("It's just a deer.");
                        appController.nextScene();
                    }
                    break;
                case 2:
                    //run away as fast as you can
                    branchLightLoop();
                    break;
                case 3:
                    // hide
                    branchCreeperLoop();
                    break;
                case 4:
                    //curl up and die
                    break;
            }
        }
    }


    // Creeper branch S3
    private void branchCreeperLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // ran from zombie
            displayCreeper();

            sceneList.get(2).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    //attack
                    zombie.setZHealth(zombie.getZHealth() - 25);
                    System.out.println("You attacked your zombie");
                    break;
                case 2:
                    //run
                    System.out.println("Run forest run...");
                    appController.nextScene();
                    branchHatchetLoop();
                    break;
                case 3:
                    //offer a snack
                    player.setHealth(player.getHealth() - 100);
                    break;
                case 4:
                    System.out.println("The highway seems more dangerous at night. You turn back around.");
                    appController.nextScene();
                    break;
            }
        }
    }

    // find a light through the trees branch
    private void branchLightLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // hid from zombie
            displayTree();

            sceneList.get(3).begin();
            System.out.println("Your health: " + player.getHealth());
            if(player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                //go to the light
                case 1:
                    Console.clear();
                    displayRescueJeep();
                    System.out.println("There's a rescue team! You join a thriving post apocalyptic community");
                    appController.nextScene();
                    quit = true;
                    break;
                case 2:
                    //fight and live another day
                    Battle battle = new Battle(player);
                    battle.begin();
                    break;
                case 3:
                    //search your backpack for steak
                    System.out.println("You're getting hungry.");
                    int choice = appController.promptForBackpackChoice(player);
                    break;
                case 4:
                    // Get lost
                    Console.clear();
                    displayWanderer();
                    System.out.println("Realizing there isn't much to live for, " +
                            "You await your final moments in this cruel and cold world..");
                    player.setHealth(player.getHealth() - 100);
                    quit = true;
                    break;
            }
        }
    }

    private void displayForest() {
        try {
            String path = "sceneArt/ForestSceneArt.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayShelter() {
        try {
            String path = "sceneArt/Shelter.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayCreeper() {
        try {
            String path = "sceneArt/Zombie.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayTree() {
        try {
            String path = "sceneArt/Trees.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayRescueJeep() {
        try {
            String path = "sceneArt/RescueJeep.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayWanderer() {
        try {
            String path = "sceneArt/Wanderer.txt";
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

    @Override
    public void end() {
    }
}
