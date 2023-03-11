package com.lastofus.appcontroller;

import java.util.Scanner;

public class LastOfUsAppController {
    private final Scanner scanner = new Scanner(System.in);


    public void execute() {
        welcome(); //title screen
        showOptionsList(); //1, 2, 3, 4 eventList
        int decision = promptForDecision();
        int backpackChoice = promptForBackpackChoice();
    }

    private int promptForBackpackChoice() {
        int backpackChoice = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            System.out.print("You have tools: " );// TODO don't hardcode 14
            System.out.print("[1] Steak");
            System.out.print("[2] Gun");
            System.out.print("[3] First Aid Kit");
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                backpackChoice = Integer.parseInt(input);
                if (1 <= backpackChoice && backpackChoice <= 3) {
                    validInput = true;
                }
            }
        }

        return backpackChoice;
        //clear console
    }

    private int promptForDecision() { //Prompts the user for an action
        int decision = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            System.out.print("Please choose wisely: [1-4] " );// TODO don't hardcode 14
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                decision = Integer.parseInt(input); //now you can safely parseint() without it blowing
                if (1 <= decision && decision <= 4) {
                    validInput = true;
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
    }

}