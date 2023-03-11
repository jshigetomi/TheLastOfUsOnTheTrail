package com.lastofus.events;

import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

class Battle implements Event{
    private List<Scene> sceneList = new ArrayList<>();
    private Player player;

    public Battle(Player currentPlayer) {
        this.player = currentPlayer;
    }

    @Override
    public void begin() {
        for(Scene scene:sceneList) {
                scene.begin();
        }
    }

    @Override
    public void updatePlayer(Player currentPlayer) {

    }

    @Override
    public void end() {

    }
}