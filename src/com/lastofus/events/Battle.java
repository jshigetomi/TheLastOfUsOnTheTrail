package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

class Battle implements Event{
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();

    public Battle(Player currentPlayer) {
        this.player = currentPlayer;
        sceneList.add(new Scene("1"));
        sceneList.add(new Scene("2"));
        sceneList.add(new Scene("3"));
        sceneList.add(new Scene("4"));
        sceneList.add(new Scene("5"));
    }

    @Override
    public void begin() {
        while(player.getHealth() > 0) {
            sceneList.get(0).begin();
            appController.execute();
            sceneList.get(1).begin();
            appController.execute();
        }
    }

    @Override
    public void updatePlayer(Player currentPlayer) {

    }

    @Override
    public void end() {

    }
}