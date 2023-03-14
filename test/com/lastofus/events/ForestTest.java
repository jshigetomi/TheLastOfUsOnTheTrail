package com.lastofus.events;

import com.lastofus.player.Player;

public class ForestTest {

    public static void main(String[] args) {
        Player player = new Player("Jessica", 100, 100);
        Event forest = new Forest(player);
        forest.begin();
    }
}