package com.lastofus.events;

import com.lastofus.items.Gun;
import com.lastofus.items.Steak;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;

public class ForestTest {

    public static void main(String[] args) {
        Player player = new Player("Jessica", 100, 100);
        player.wearBackpack(new Backpack(new Gun(1)));
        Event forest = new Forest(player);

        forest.begin();
    }

    @org.junit.Test
    public void begin() {
        Player player = new Player("Jessica", 100, 100);
        Event forest = new Forest(player);
        forest.begin();
    }

    @org.junit.Test
    public void updatePlayer() {
    }


    public void end() {
    }
}