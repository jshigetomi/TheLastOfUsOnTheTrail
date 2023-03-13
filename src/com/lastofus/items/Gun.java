package com.lastofus.items;

import com.lastofus.player.Item;
import com.lastofus.player.Player;

public class Gun extends Item {

    private int charges;

    public Gun(int charges) {
        this.charges = charges;
    }

    @Override
    public void use(Player player) {
        if(charges > 0) {
            player.setAttack(player.getAttack() + 50);
            System.out.println("You used a gun and gained 50 attack");
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