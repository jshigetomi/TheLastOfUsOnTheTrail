package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    public LastOfUsAppController appController = new LastOfUsAppController();
    public Player player = null;
    public List<Scene> sceneList = new ArrayList<>();

    public Event () {
    }

    public void begin() {
        System.out.println("Error: begin() not implemented");
    }

    public void end() {
        System.out.println("Error: end() not implemented");
    }
}
