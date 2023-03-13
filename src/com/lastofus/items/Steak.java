package com.lastofus.items;

import com.lastofus.player.Item;
import com.lastofus.player.Player;

public class Steak extends Item {

    private int charges;

    public Steak(int charges) {
        this.charges = charges;
    }

    public void use(Player player) {
        if(charges > 0) {
            player.setHealth(player.getHealth() + 100);
            System.out.println("You used a steak and gained 100 health");
            charges--;
        } else {
            System.out.println("You have no more charges");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}