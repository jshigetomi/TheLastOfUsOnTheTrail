package com.lastofus.events;

import com.apps.util.Console;
import com.lastofus.appcontroller.LastOfUsAppController;
import com.lastofus.player.Player;
import jdk.jfr.Event;

import java.util.ArrayList;
import java.util.List;

public class Highway extends Event {
    private final LastOfUsAppController appController = new LastOfUsAppController();
    private final Player player;
    private final List<Scene> sceneList = new ArrayList<>();

    public Highway(Player player) {
        super();
        this.player = player;
        sceneList.add(new Scene("Highway" , "1"));
        sceneList.add(new Scene("Highway", "2"));
        sceneList.add(new Scene("Highway" , "3"));
        sceneList.add(new Scene("Highway", "4"));
        sceneList.add(new Scene("Highway" , "5"));
    }


}
