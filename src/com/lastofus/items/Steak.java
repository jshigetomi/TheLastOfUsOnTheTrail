package com.lastofus.items;

import com.lastofus.player.Item;
import com.lastofus.player.Player;

public class Steak extends Item {

    private int charges;
    public int damage;  // this one doesn't make sense. Shouldn't it just be use?
    public int use;

    public Steak(int charges) {
        this.charges = charges;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = charges - use;
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}