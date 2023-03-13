package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.items.Gun;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Battle extends Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();
    private final Zombie zombie = new Zombie();

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

        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();

            /*
             * Add art here
             */

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
                    branchOneLoop();
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    // hide
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    if(!player.getBackpack().getItems().isEmpty()) {
                        if (player.getBackpack().getItem(choice - 1) instanceof Gun) {
                            branchGunLoop();
                        }
                    }
                    break;
            }
        }
        end();
    }

    private void branchGunLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // attacked the zombie with a gun.
            if(zombie.getZHealth() <= 0 ) {
                break;
            }
            sceneList.get(6).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    zombie.setZHealth(zombie.getZHealth() - player.getAttack());
                    branchGunLoop();
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
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
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // attacked the zombie with bare hands.
            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    branchOneLoop();
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    // hide
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    break;
            }
        }
    }


    // running branch
    private void branchTwoLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
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
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice(player);
                    break;
            }
        }
    }

    // hiding branch
    private void branchThreeLoop() {
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
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    branchOneLoop();
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    // scream
                    player.setHealth(player.getHealth() - 30);
                    System.out.println("You were slashed by the zombie. You lost 30 health.");
                    deathLoop();
                    break;
            }
        }
    }

    private void deathLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // slashed by zombie
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
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    player.setHealth(player.getHealth() - 30);
                    System.out.println("You were slashed by the zombie. You lost 30 health.");
                    deathLoop();
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