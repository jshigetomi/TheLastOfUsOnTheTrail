package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Player;
import com.lastofus.events.Event;

import java.util.ArrayList;
import java.util.List;

public class Highway extends Event {
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();
    private boolean quit = false;

    public Highway(Player player) {
        super();
        this.player = player;
        sceneList.add(new Scene("Highway", "1"));
        sceneList.add(new Scene("Highway", "2"));
        sceneList.add(new Scene("Highway", "3"));
        sceneList.add(new Scene("Highway", "4"));
        sceneList.add(new Scene("Highway", "5"));
    }

    @Override
    public void begin() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // Enter highway
            sceneList.get(0).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    lambLoop();
                    break;
                case 2:
                    sheriffLoop();
                    break;
                case 3:
                    ambulanceLoop();
                    break;
                case 4:
                    System.out.println("Feeling brave? You went out in the open");
                    quit = true;
                    break;
            }
        }
    }

    // acquire steak branch
    private void lambLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // kill lamb for steak
            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    player.getBackpack().addItem(new Steak(1));
                    System.out.println("You acquired a steak into your backpack.");
                    Console.pause(1000); // temp fix
                    lambLoop();
                    break;
                case 2:
                    System.out.println("It is so cute, you can't kill it!!");
                    lambLoop();
                    break;
                case 3:
                    System.out.println("STAY ALERT: You have " + player.getBackpack().viewLoad()
                            + "  in your backpack.");
                    Console.pause(1000); // temp fix
                    lambLoop();
                    break;
                case 4:
                    begin();
                    break;
            }
        }
    }

    // find gun branch
    private void sheriffLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // search vehicle and find gun
            sceneList.get(2).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    player.getBackpack().addItem(new Gun(1));
                    System.out.println("Congratulations, you found a gun and placed it into your backpack.");
                    Console.pause(1000); // temp fix
                    begin();
                    break;
                case 2:
                    System.out.println("Stop being a scaredy cat and look around");
                    sheriffLoop();
                    break;
                case 3:
                    System.out.println("STAY ALERT: You have " + player.getBackpack().viewLoad()
                            + "  in your backpack.");
                    Console.pause(1000); // temp fix
                    sheriffLoop();
                    break;
                case 4:
                    begin();
                    break;
            }
        }
    }

    // find medkit branch
    private void ambulanceLoop() {
        while (player.getHealth() > 0 && !sceneList.isEmpty() && !quit) {
            Console.clear();
            // search vehicle and find medkit
            sceneList.get(3).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    player.getBackpack().addItem(new MedKit(1));
                    System.out.println("Excellent find! You added a medical kit your backpack.");
                    Console.pause(1000); // temp fix
                    begin();
                    break;
                case 2:
                    System.out.println("You found sleeping pills instead of a medkit. You're not a doctor!");
                    ambulanceLoop();
                    break;
                case 3:
                    System.out.println("STAY ALERT: You have " + player.getBackpack().viewLoad()
                            + "  in your backpack.");
                    Console.pause(1000); // temp fix
                    ambulanceLoop();
                    break;
                case 4:
                    begin();
                    break;
            }
        }
    }

}
