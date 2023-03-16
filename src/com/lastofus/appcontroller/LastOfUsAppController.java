package com.lastofus.appcontroller;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.lastofus.events.*;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LastOfUsAppController {
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random(); //Random attack on player
    private Player player1;
    private final Prompter prompter = new Prompter(new Scanner(System.in));

    private final List<Event> eventList = new ArrayList<>();
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void execute() {
        String quit = "1";
        while(!quit.equals("2")) {
            welcome(); //title screen
            nextScene();
            initializePlayer(); // initialize player
            loadEvents(); // add events to eventList

            // play all events
            for (Event event : eventList) {
                event.begin();
            }

            end();
            quit = promptForExit();
            eventList.clear();
        }
    }

    private void end() {
        Console.clear();
        if(player1.hasFriend()) {
            twistEnd();
        }
        else if(player1.getHealth() == 0) {
            youDied();
        }
        else {
            theEnd();
        }
    }

    public void initializePlayer() {
        player1 = new Player("Joel", 100, 50);
        Backpack jansport = new Backpack();
//        jansport.addItem(steak);
//        jansport.addItem(medKit);
//        jansport.addItem(new Gun(1));

        player1.wearBackpack(jansport);
    }

    public void loadEvents() {
        eventList.add(new Intro(player1));
        eventList.add(new Highway(player1));
        eventList.add(new Battle(player1));
        eventList.add(new Forest(player1));
    }

    public int promptForBackpackChoice(Player player1) {
        int backpackChoice = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            Console.clear();
            System.out.println(player1.getBackpack().viewLoad());
            System.out.println(ANSI_RED + "Your current health is: " + player1.getHealth() + ANSI_RESET);
            if(player1.hasGun()) {
                System.out.println("Gun Equipped.");
            }
            if(player1.getBackpack().getLoad() == 0) {
                nextScene();
                break;
            }
            String input = scanner.nextLine().trim(); //BLOCKS for input // TODO: tell the player what the item does

            if (input.matches("\\d{1}")) { //any digit one
                backpackChoice = Integer.parseInt(input);
                if (1 <= backpackChoice && backpackChoice <= player1.getBackpack().getLoad() || backpackChoice == 6) {
                    String decision;
                    switch (backpackChoice) {
                        case 1:
                            Console.clear();
                            player1.getBackpack().getItems().get(0).menu(player1);
                            decision = prompter.prompt("","1|2","");
                            if(decision.equals("1")) {
                                player1.getBackpack().getItems().get(0).use(player1);
                                player1.getBackpack().removeItem(0);
                            }
                            break;
                        case 2:
                            Console.clear();
                            player1.getBackpack().getItems().get(1).menu(player1);
                            decision = prompter.prompt("","1|2","");
                            if(decision.equals("1")) {
                                player1.getBackpack().getItems().get(1).use(player1);
                                player1.getBackpack().removeItem(1);
                            }
                            break;
                        case 3:
                            Console.clear();
                            player1.getBackpack().getItems().get(2).menu(player1);
                            decision = prompter.prompt("","1|2","");
                            if(decision.equals("1")) {
                                player1.getBackpack().getItems().get(2).use(player1);
                                player1.getBackpack().removeItem(2);
                            }
                            break;
                        case 4:
                            Console.clear();
                            player1.getBackpack().getItems().get(3).menu(player1);
                            decision = prompter.prompt("","1|2","");
                            if(decision.equals("1")) {
                                player1.getBackpack().getItems().get(3).use(player1);
                                player1.getBackpack().removeItem(3);
                            }
                            break;
                        case 5:
                            Console.clear();
                            player1.getBackpack().getItems().get(4).menu(player1);
                            decision = prompter.prompt("","1|2","");
                            if(decision.equals("1")) {
                                player1.getBackpack().getItems().get(4).use(player1);
                                player1.getBackpack().removeItem(4);
                            }
                            break;
                        case 6:
                            validInput = true;
                            break;
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
                }
            }
        }
        return decision;
    }

    private void showOptionsList() {
    // eventList
    }

    private void welcome() {
        Console.clear();
        try {
            String path = "scenes/Welcome.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextScene() {
        String input = prompter.prompt("Press enter to continue", ".*", "Invalid input");
    }

    public String promptForExit() {
        return prompter.prompt("1 to play again, 2 to quit", "1|2", "Invalid input\n");
    }

    public void youDied() {
        try {
            String path = "scenes/youDied.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void theEnd() {
        try {
            String path = "scenes/TheEnd.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void twistEnd() {
        try {
            String path = "scenes/TwistEnding.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}