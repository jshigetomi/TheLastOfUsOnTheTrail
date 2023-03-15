package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    protected LastOfUsAppController appController = new LastOfUsAppController();
    protected Player player = null;
    protected List<Scene> sceneList = new ArrayList<>();

    public Event (Player player) {
        this.player = player;
    }

    public abstract void begin();


    public void end(){

    };
}
