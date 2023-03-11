package com.lastofus.events;

import com.lastofus.player.Player;

import static org.junit.Assert.*;

public class BattleTest {

        @org.junit.Test
        public void begin() {
            Player player = new Player("Justin", 100, 100);
            Event battle = new Battle(player);
            battle.begin();
        }

        @org.junit.Test
        public void updatePlayer() {
        }

        @org.junit.Test
        public void end() {
        }
}