package com.lastofus.appcontroller;

import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;

import java.util.Random;
import java.util.Scanner;

public class LastOfUsAppController {
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random(); //Random attack on player
    private final Player player1 = new Player("Player1", 100, 100);
    private final Steak steak = new Steak();
    private final MedKit medKit = new MedKit();
    private final Gun gun = new Gun();


    public void execute() {
        welcome(); //title screen
        showOptionsList(); //1, 2, 3, 4 eventList
        int decision = promptForDecision();
        int backpackChoice = promptForBackpackChoice();
    }

    public int promptForBackpackChoice() {
        int backpackChoice = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            System.out.print("You have tools: " );
            System.out.print("[1] Steak");
            System.out.print("[2] Gun");
            System.out.print("[3] First Aid Kit");
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                backpackChoice = Integer.parseInt(input);
                if (1 <= backpackChoice && backpackChoice <= 3) {
                    validInput = true;

                    if (backpackChoice == 1) {
                        steak.setUse(20);
                    } else if (backpackChoice == 2) {
                        medKit.setUse(20);
                    } else {
                        gun.setDamage(20);
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
            System.out.print("Choose your path: [1-4] " );
            System.out.print("[1] Run");
            System.out.print("[2] Eat");
            System.out.print("[3] Sleep");
            System.out.print("[4] Fight");
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                decision = Integer.parseInt(input); //now you can safely parseint() without it blowing
                if (1 <= decision && decision <= 4) {
                    validInput = true;
                    if (decision == 1) {
                        //TODO come back to this
                    } else if (decision == 2) {
                        promptForBackpackChoice(); // TODO make it pull out steak
                    } else if (decision == 3) {
                        System.out.println(); //TODO Raise health by 10
                    } else {
                       //zombie.setHealth()?? TODO: lower zombies health by a random int
                    }
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