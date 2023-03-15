package com.lastofus.events;

import com.lastofus.items.Gun;
import com.lastofus.items.MedKit;
import com.lastofus.items.Steak;
import com.lastofus.player.Backpack;
import com.lastofus.player.Player;

import static org.junit.Assert.*;

public class IntroTest {

    public static void main(String[] args) {
        Player player = new Player("Justin", 100, 100);
        player.wearBackpack(new Backpack(new Steak(1), new Gun(1), new MedKit(1)));
        Event intro = new Intro(player);
        intro.begin();
    }
}