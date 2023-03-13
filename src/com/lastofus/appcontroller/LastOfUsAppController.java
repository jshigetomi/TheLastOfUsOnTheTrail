package com.lastofus.appcontroller;

import com.lastofus.events.Battle;
import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;
import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LastOfUsAppController {
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random(); //Random attack on player
    private final Player player1 = new Player("Player1", 100, 100);
    private final Backpack jansport = new Backpack();
    private final Steak steak = new Steak(1);
    private final MedKit medKit = new MedKit(1);
    private final Gun gun = new Gun(1);

    private List<Battle> eventList = new ArrayList<>(); // TODO: Ask Jay why this wont work with Event


    public void execute() {
        welcome(); //title screen
        player1.wearBackpack(jansport); // give player a backpack
        eventList.add(new Battle(player1));
        eventList.get(0).begin();
    }

    public int promptForBackpackChoice(Player player1) {
        int backpackChoice = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            System.out.print("You have tools: " );
            System.out.println(player1.getBackpack().viewLoad());
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                backpackChoice = Integer.parseInt(input);
                if (1 <= backpackChoice && backpackChoice <= player1.getBackpack().getLoad()) {
                    validInput = true;

                    if (backpackChoice == 2) {
                        steak.setUse(steak.getUse() - 1);
                        player1.setHealth(player1.getHealth() + 50);
                        System.out.println("You ate the steak and gained 50 health");
                    } else if (backpackChoice == 1) {
                        medKit.setUse(medKit.getUse() - 1);
                        player1.setHealth(player1.getHealth() + 100);
                        System.out.println("You used the MedKit and gained 50 health");
                    } else {
                        gun.setUse(gun.getUse() - 1);
                        player1.setAttack(player1.getAttack() + 50);
                        System.out.println("You equipped the gun and gained 50 attack");
                    }
                }
            }
        }

        return backpackChoice;
        //clear console
    }

    public int promptForDecision() { //Prompts the user for an action
        int decision = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                decision = Integer.parseInt(input); //now you can safely parseint() without it blowing
                if (1 <= decision && decision <= 4) {
                    validInput = true;
//                    if (decision == 1) {
//                        //TODO come back to this
//                    } else if (decision == 2) {
//                        promptForBackpackChoice(); // TODO make it pull out steak
//                    } else if (decision == 3) {
//                        System.out.println(); //TODO Raise health by 10
//                    } else {
//                       //zombie.setHealth()?? TODO: lower zombies health by a random int
//                    }
                }
            }
        }

        return decision;
    }

    private void showOptionsList() {
    // eventList
    }

    private void welcome() {
        System.out.println("------------The Last of Us----------"); //Files.readString("Banner.txt")
        System.out.println("--------A Zombie has appeared:------");
        System.out.println("--------A Zombie has appeared:------");
    }

}