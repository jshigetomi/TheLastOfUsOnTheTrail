package com.lastofus.player;

import com.lastofus.items.Gun;
import com.lastofus.items.Steak;
import org.junit.Test;

import static org.junit.Assert.*;

public class BackpackTest {
    @Test
    public void testBackpack() {
        Backpack backpack = new Backpack();
        assertEquals(0, backpack.getLoad());
        backpack.addItem(new Gun(1));
        assertEquals(1, backpack.getLoad());
        backpack.getItems().indexOf(new Gun(1));
        backpack.addItem(new Steak(1));
        System.out.println(backpack.getItems().indexOf(new Gun(1)));
    }
}