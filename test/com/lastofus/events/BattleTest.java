package com.lastofus.events;

import com.lastofus.player.Player;

public class BattleTest {

    public static void main(String[] args) {
        Player player = new Player("Justin", 100, 100);
        Event battle = new Battle(player);
        battle.begin();
    }

    @org.junit.Test
    public void begin() {
        Player player = new Player("Justin", 100, 100);
        Event battle = new Battle(player);
        battle.begin();
    }

    @org.junit.Test
    public void updatePlayer() {
    }


    public void end() {
    }
}