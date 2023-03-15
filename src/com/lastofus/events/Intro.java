package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.player.Player;

public class Intro extends Event {
    private boolean quit = false;

    public Intro(Player player) {
        super(player);
        sceneList.add(new Scene("Intro", "1"));
        sceneList.add(new Scene("Intro", "2"));
    }

    @Override
    public void begin() {
        while (!sceneList.isEmpty() && !quit) {
            Console.clear();
            sceneList.get(0).begin();
            int decision = appController.promptForDecision();
            switch(decision) {
                case 1:
                   appController.nextScene();
                    break;
                case 2:
                    appController.nextScene();
                    break;
        }
    }


}
