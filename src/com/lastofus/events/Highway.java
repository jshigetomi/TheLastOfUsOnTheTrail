package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Highway extends Event {
    private boolean quit = false;

    private int lambCounter = 0;
    private int gunCounter = 0;
    private int kitCounter = 0;

    public Highway(Player player) {
        super(player);
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

            displayHighway();

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
                    System.out.println("You see a zombie in the distance blocking your way forward.");
                    appController.nextScene();
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
            if(lambCounter == 0) {
                displayLamb();
            }
            else {
                displayDeadLamb();
            }
            sceneList.get(1).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if(lambCounter == 0) {
                        Console.clear();
                        lambCounter++;
                        Steak steak = new Steak(1);
                        player.getBackpack().addItem(steak);
                        steak.display();
                        System.out.println("You acquired a steak into your backpack.");
                    }
                    else {
                        System.out.println("You already killed the poor thing!");
                    }
                    appController.nextScene();
                    lambLoop();
                    break;
                case 2:
                    if(lambCounter == 0) {
                        System.out.println("It is so cute, you can't kill it!!");
                    }
                    else {
                        System.out.println("The body is still warm....");
                    }
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
            displaySheriffCar();

            sceneList.get(2).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if(gunCounter == 0) {
                        Console.clear();
                        gunCounter++;
                        Gun gun = new Gun(1);
                        player.getBackpack().addItem(gun);
                        gun.display();
                        System.out.println("Congratulations, you found a gun and placed it into your backpack.");
                        appController.nextScene();
                    }
                    else if(gunCounter == 1) {
                        Console.clear();
                        gunCounter++;
                        Gun gun = new Gun(1);
                        player.getBackpack().addItem(gun);
                        gun.display();
                        System.out.println("You looked a bit more and found another gun!");
                        appController.nextScene();
                    }
                    else {
                        System.out.println("There's nothing useful in the sheriff car anymore");
                        appController.nextScene();
                    }
                    sheriffLoop();
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
            displayAmbulance();

            sceneList.get(3).begin();
            System.out.println("Your health: " + player.getHealth());
            System.out.println("Choose wisely [1-4]");
            int decision = appController.promptForDecision();
            switch (decision) {
                case 1:
                    if(kitCounter == 0) {
                        Console.clear();
                        kitCounter++;
                        MedKit medKit = new MedKit(1);
                        player.getBackpack().addItem(medKit);
                        medKit.display();
                        System.out.println("Excellent find! You added a medical kit your backpack.");
                        appController.nextScene();
                    }
                    else {
                        System.out.println("There is nothing useful in the ambulance anymore.");
                        appController.nextScene();
                    }
                    ambulanceLoop();
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

    private void displayHighway() {
        try {
            String path = "sceneArt/Highway.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displaySheriffCar() {
        try {
            String path = "sceneArt/SheriffCar.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayAmbulance() {
        try {
            String path = "sceneArt/Ambulance.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayLamb() {
        try {
            String path = "sceneArt/Lamb.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayDeadLamb() {
        try {
            String path = "sceneArt/DeadLamb.txt";
            // read the entire file as a string
            String contents = Files.readString(Path.of(path));
            System.out.println(contents);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
