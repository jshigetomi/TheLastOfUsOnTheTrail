package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

interface Event {
    LastOfUsAppController appController = new LastOfUsAppController();
    Player player = null;
    List<Scene> sceneList = new ArrayList<>();
    void begin();
    void updatePlayer(Player currentPlayer);
    void end();
}
