package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Forest extends Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private Player player;
    private final List<Scene> sceneList = new ArrayList<>();
    private final Zombie zombie = new Zombie();
    private final Highway highway = new Highway(player);

    public Forest(Player currentPlayer) {
        super();
        this.player = currentPlayer;
        sceneList.add(new Scene("Forest", "1"));
        sceneList.add(new Scene("Forest", "2"));
        sceneList.add(new Scene("Forest","3"));
        sceneList.add(new Scene("Forest","4"));
    }

    public void begin() {

        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            /*
             * Add art here
             */

            sceneList.get(1).begin();
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
                    Console.pause(4000L);
                    branchCampLoop();
                    break;
                case 2:
                    //build a trap
                    System.out.println("You caught a zombie. Time to kill it.");
                    player.setAttack(75); //check to see if this is functional
                    break;
                case 3:
                    // keep running into the abyss
                    Battle battle = new Battle(player);
                    battle.begin(); //keep running
                    break;
                case 4:
                    //hide in a tree
                    branchCreeperLoop();
                    break;
            }
        }
        end();
    }


    private void branchForestLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // scene 1
            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() + 20);
                    System.out.println("Whew...A spot to rest for the night. You earned 20 health.");
                    Console.pause(4000L);
                    branchCampLoop();
                    break;
                case 2:
                    //build a trap
                    System.out.println("You caught a zombie. Time to kill it.");
                    player.setAttack(75); //check to see if this is functional
                    break;
                case 3:
                    // keep running into the abyss
                    Battle battle = new Battle(player);
                    battle.begin(); //keep running
                    break;
                case 4:
                    //hide in a tree
                    branchCreeperLoop();
                    break;
            }
        }
    }
    // Set up camp
    private void branchCampLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // scene 2
            sceneList.get(2).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    //investigate
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("You have ran into a zombie");
                    Battle battle = new Battle(player);
                    battle.begin();
                    break;
                case 2:
                    //run away as fast as you can
                    System.out.println("Run forest run...");
                    battle = new Battle(player);
                    battle.begin();
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
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // ran from zombie
            sceneList.get(3).begin();
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
                    //System.out.println("Run forest run...");
                    Battle battle = new Battle(player);
                    battle.begin();
                    break;
                case 3:
                    //offer a snack
                    player.setHealth(player.getHealth() - 100);
                    System.out.println("He eats you.");
                    break;
                case 4:
                    System.out.println("You found the highway.");
                    highway.begin();
                    break;
            }
        }
    }

    // find a light through the trees branch
    private void branchLightLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // hid from zombie
            sceneList.get(4).begin();
            System.out.println("Your health: " + player.getHealth());
            if(player.getHealth() <= 0) {
                break;
            }
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                //go to the light
                case 1:
                    System.out.println("There's a car");
                    highway.begin();
                    break;
                case 2:
                    //fight and live another day
                    Battle battle = new Battle(player);
                    battle.begin();
                    break;
                case 3:
                    //search your backpack for steak
                    System.out.println("You're getting hungry");
                    int choice = appController.promptForBackpackChoice(player);
                    break;
                case 4:
                    // Get lost
                    System.out.println("You await your final moments.");
                    player.setHealth(player.getHealth() - 100);
                    break;
            }
        }
    }

    @Override
    public void end() {
        if(zombie.getZHealth() <= 0 ) {
            System.out.println("You killed the zombie. You win!");
        }

        else if(player.getHealth() <= 0) {
            System.out.println("The zombie ate your brains. You lose.");
        }
    }
    }
