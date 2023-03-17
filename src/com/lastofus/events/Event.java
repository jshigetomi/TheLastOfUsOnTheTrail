package com.lastofus.events;

import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Event {
    protected LastOfUsAppController appController = new LastOfUsAppController();
    protected Player player = null;
    protected List<Scene> sceneList = new ArrayList<>();

    public static final String ANSI_RED = "\u001B[31m";     //console color for the health bar mainly
    public static final String ANSI_RESET = "\u001B[0m";

    public Event (Player player) {
        this.player = player;
    }

    public abstract void begin();


    public void end(){

    };
}
