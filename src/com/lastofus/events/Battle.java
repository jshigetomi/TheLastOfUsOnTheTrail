package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

class Battle implements Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();

    public Battle(Player currentPlayer) {
        this.player = currentPlayer;
        sceneList.add(new Scene("1"));
        sceneList.add(new Scene("2"));
        sceneList.add(new Scene("3"));
        sceneList.add(new Scene("4"));
        sceneList.add(new Scene("5"));
        sceneList.add(new Scene("6"));
    }

    @Override
    public void begin() {
        // while the player is still alive and the sceneList is not empty
        // play the first scene and prompt the user for input
        Console.clear();
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            sceneList.get(0).begin();
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
                    int choice = appController.promptForBackpackChoice();
                    break;
            }
        }
    }


    // fighting branch
    private void branchOneLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // attacked the zombie with bare hands.
            player.setHealth(player.getHealth() - 10);
            System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
            sceneList.get(1).begin();
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
                    int choice = appController.promptForBackpackChoice();
                    break;
            }
            end();
        }
    }


    // running branch
    private void branchTwoLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // ran from zombie
            sceneList.get(5).begin();
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    int choice = appController.promptForBackpackChoice();
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
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    deathLoop();
                    break;
            }
        }
    }

    private void deathLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty()) {
            Console.clear();
            // slashed by zombie
            player.setHealth(player.getHealth() - 30);
            System.out.println("You were slashed by the zombie. You lost 30 health.");
            sceneList.get(3).begin();
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                    player.setHealth(player.getHealth() - 10);
                    System.out.println("Punching the zombie in the face did not work. You lost 10 health.");
                    break;
                case 2:
                    branchTwoLoop();
                    break;
                case 3:
                    branchThreeLoop();
                    break;
                case 4:
                    deathLoop();
                    break;
            }
        }
        end();
    }


    @Override
    public void updatePlayer(Player currentPlayer) {

    }

    @Override
    public void end() {
        System.out.println("You were killed by the Zombie!");
    }
}