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
        while (!sceneList.isEmpty() && !quit) {
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
        while (!sceneList.isEmpty() && !quit) {
            Console.clear();
            // kill lamb for steak
            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    Console.clear();
                    Steak steak = new Steak(1);
                    player.getBackpack().addItem(steak);
                    steak.display();
                    System.out.println("You acquired a steak into your backpack.");
                    appController.nextScene();
                    lambLoop();
                    break;
                case 2:
                    System.out.println("It is so cute, you can't kill it!!");
                    appController.nextScene();
                    lambLoop();
                    break;
                case 3:
                    int choice = appController.promptForBackpackChoice(player);
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
        while (!sceneList.isEmpty() && !quit) {
            Console.clear();
            // search vehicle and find gun
            sceneList.get(2).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    Console.clear();
                    Gun gun = new Gun(1);
                    player.getBackpack().addItem(gun);
                    gun.display();
                    System.out.println("Congratulations, you found a gun and placed it into your backpack.");
                    appController.nextScene();
                    begin();
                    break;
                case 2:
                    System.out.println("Stop being a scaredy cat and look around");
                    appController.nextScene();
                    sheriffLoop();
                    break;
                case 3:
                    int choice = appController.promptForBackpackChoice(player);
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
        while (!sceneList.isEmpty() && !quit) {
            Console.clear();
            // search vehicle and find medkit
            sceneList.get(3).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    Console.clear();
                    MedKit medKit = new MedKit(1);
                    player.getBackpack().addItem(medKit);
                    medKit.display();
                    System.out.println("Excellent find! You added a medical kit your backpack.");
                    appController.nextScene();
                    begin();
                    break;
                case 2:
                    System.out.println("You found sleeping pills instead of a medkit. You're not a doctor!");
                    appController.nextScene();
                    ambulanceLoop();
                    break;
                case 3:
                    int choice = appController.promptForBackpackChoice(player);
                    ambulanceLoop();
                    break;
                case 4:
                    begin();
                    break;
            }
        }
    }

}
