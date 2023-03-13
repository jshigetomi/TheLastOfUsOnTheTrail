package com.lastofus.appcontroller;

import com.lastofus.events.Event;
import com.lastofus.events.Battle;
import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LastOfUsAppController {
    private final Scanner scanner = new Scanner(System.in);
    Random random = new Random(); //Random attack on player
    private Player player1;
    private Backpack jansport;
    private final Steak steak = new Steak(1);
    private final MedKit medKit = new MedKit(1);
    private final Gun gun = new Gun(1);

    private final List<Battle> eventList = new ArrayList<>();


    public void execute() {
        welcome(); //title screen
        initializePlayer(); // initialize player
        loadEvents(); // add events to eventList
        for(Event event: eventList) {
            event.begin();
        }
    }

    public void initializePlayer() {
        player1 = new Player("Joel", 100, 50);
        jansport = new Backpack();
//        jansport.addItem(steak);
//        jansport.addItem(medKit);
//        jansport.addItem(gun);
        player1.wearBackpack(jansport);
    }

    public void loadEvents() {
        eventList.add(new Battle(player1));
    }

    public int promptForBackpackChoice(Player player1) {
        int backpackChoice = 0;

        boolean validInput = false; //assume its wrong
        while (!validInput) {
            System.out.println(player1.getBackpack().viewLoad());
            if(player1.getBackpack().getLoad() == 0) {
                break;
            }
            String input = scanner.nextLine().trim(); //BLOCKS for input
            if (input.matches("\\d{1}")) { //any digit one
                backpackChoice = Integer.parseInt(input);
                if (1 <= backpackChoice && backpackChoice <= player1.getBackpack().getLoad()) {
                    validInput = true;
                    switch (backpackChoice) {
                        case 1:
                            player1.getBackpack().getItems().get(0).use(player1);
                            break;
                        case 2:
                            player1.getBackpack().getItems().get(1).use(player1);
                            break;
                        case 3:
                            player1.getBackpack().getItems().get(2).use(player1);
                            break;
                        case 4:
                            player1.getBackpack().getItems().get(3).use(player1);
                            break;
                        case 5:
                            player1.getBackpack().getItems().get(4).use(player1);
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
        System.out.println("------------The Last of Us----------"); //Files.readString("Banner.txt")
        System.out.println("--------A Zombie has appeared:------");
        System.out.println("--------A Zombie has appeared:------");
    }
}