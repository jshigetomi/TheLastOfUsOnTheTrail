package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Forest extends Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();
    private final Zombie zombie = new Zombie();

    public Forest(Player currentPlayer) {
        super();
        this.player = currentPlayer;
        sceneList.add(new Scene("Forest", "8"));
        sceneList.add(new Scene("Forest", "2"));
        sceneList.add(new Scene("Forest","3"));
        sceneList.add(new Scene("Forest","4"));
        sceneList.add(new Scene("Forest","5"));
        sceneList.add(new Scene("Forest","6"));
        sceneList.add(new Scene("Forest","7"));
    }

    public void begin() {

    }
}